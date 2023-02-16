import java.util.*;
import java.lang.Math;
import java.io.*;


public class csvTrial
{
   static void merge(int arr[], int l, int m, int r)
   {
       // Find sizes of two subarrays to be merged
      int n1 = m - l + 1;
      int n2 = r - m;
   
       /* Create temp arrays */
      int L[] = new int [n1];
      int R[] = new int [n2];
   
       /*Copy data to temp arrays*/
      for (int i=0; i<n1; ++i)
         L[i] = arr[l + i];
      for (int j=0; j<n2; ++j)
         R[j] = arr[m + 1+ j];
   
   
       /* Merge the temp arrays */
   
       // Initial indexes of first and second subarrays
      int i = 0, j = 0;
   
       // Initial index of merged subarry array
      int k = l;
      while (i < n1 && j < n2)
      {
         if (L[i] <= R[j])
         {
            arr[k] = L[i];
            i++;
         }
         else
         {
            arr[k] = R[j];
            j++;
         }
         k++;
      }
   
       /* Copy remaining elements of L[] if any */
      while (i < n1)
      {
         arr[k] = L[i];
         i++;
         k++;
      }
   
       /* Copy remaining elements of R[] if any */
      while (j < n2)
      {
         arr[k] = R[j];
         j++;
         k++;
      }
   }

   static void sort(int arr[], int l, int r)
   {
      if (l < r)
      {
           // Find the middle point
         int m = (l+r)/2;
      
           // Sort first and second halves
         sort(arr, l, m);
         sort(arr , m+1, r);
      
           // Merge the sorted halves
         merge(arr, l, m, r);
      }
   }

   public static void main(String args[])
   {
      Random rand = new Random();
   
      Date date = new Date();
      int arrn[]=new int[100];
      double fx[]=new double[100];
      int count=0;
   //Testing results for 100 different arrays from size 1000 to 100000 varying by 1000
      for(int n=1000;n<=100000;n=n+1000){
      
         arrn[count]=n;
         int arr[] = new int[n];
         for(int i=0;i<n;i++){
            arr[i]=rand.nextInt(10000);
         }
         date = new Date();
         long start = date.getTime();
         sort(arr, 0, arr.length-1);
         date = new Date();
         long end = date.getTime();
         fx[count]=(end-start)/ (n*Math.log(n)); //T(n)/n*log(n)
         count++;
         
      }
      
      try {
         FileWriter myWriter = new FileWriter("TrialCSV.csv");
         myWriter.write("n, T(n)/n*log(n)\");
         myWriter.write("Try, this, for, size");
         myWriter.close();
         System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   
   
      System.out.println("n values");
      System.out.println(Arrays.toString(arrn));
      System.out.println("T(n)/n*log(n) values");
      System.out.println(Arrays.toString(fx));
   
   
   
   
   
   
   
   }
}