
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class FileTest
{
   public static void main(String[] args)
   {
      try
      {
         FileWriter fWrite = new FileWriter("stats.csv"); // reading file
         Random r = new Random(); //creating random 
         int G[][] = new int[1000][1000]; //defining array
         for(int i=0;i<1000;i++) //starting loop less than 1000
            for(int j = 0;j<1000;j++) //starting inner loop less than 1000
               G[i][j] = r.nextInt();  //equating array to random and passsing it                        for (int i = 100;i<=1000;i+=100)
         {
            int M[][] = new int[i][i];
            for(int j = 0;j<i;j++)
               for(int k =0;k<i;k++)
                  M[j][k] = G[j][k];
            final long t0 = System.nanoTime();
            for(int j = 0;j<i;j++)
               for(int k =0;k<i;k++)
               {
                  int buffer = M[j][k];
                  M[j][k] = M[k][j];
                  M[k][j] = buffer;                                       
               }
            final long t1 = System.nanoTime();
            long temp = (t1-t0);
            fWrite.write(temp+","+(temp*1.0/i)+","+(temp*1.0/(i*i))+","+(temp*1.0/(i*i*i))+"\n");  //writing 
            System.out.println(temp+" "+i);
         }
         fWrite.close(); //closing the file
      } catch(IOException e) {
         System.out.println("Error in file writing!"); //outputing the if we cannot write on file
         e.printStackTrace();
      }
   }
}