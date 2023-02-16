import java.io.*;
import java.text.*;
import java.util.*;

public class M5Program{

   public static final int MAX_VALUE = 0x7ffffffe;

//Naive sorting algorithm
   public static void NaiveSort(int A[]) {
      int n = A.length;
      for(int i = 0; i < n-1; i++) {
         for(int j = 0; j < n-i-1; j++) {
            if (A[j] > A[j+1])
            {
               int temp = A[j];
               A[j] = A[j+1];
               A[j+1] = temp;
            
            }
         }
      }
   }

   //Implemented merge pseudocode
   public static void merge(int A[], int p, int q, int r) {
      int n1 = q - p + 1;
      int n2 = r - q;
   
      // Let L[1..n1 + 1] and R[1..n2 + 1] be new arrays
      int L[] = new int [n1];
      int R[] = new int [n2];
   
      for (int i = 0; i < n1; ++i) {
         L[i] = A[p + i];
      }
      for (int j = 0; j < n2; ++j) {
         R[j] = A[q + j + 1];
         // R[j] = A[q + j];
      }
   
      //Use 0x0fff ffff for infinity
   
      int i = 0;
      int j = 0;
      int k = p;
      while (i < n1 && j < n2) {
         if (L[i] <= R[j]) {
            A[k] = L[i];
            i++;
         }
         else {
            A[k] = R[j];
            j++;
         }
         k++;
      }
      while (i < n1) {
         A[k] = L[i];
         i++;
         k++;
      }
      while (j < n2) {
         A[k] = R[j];
         j++;
         k++;
      }
   }

   //Implemented MergeSort .
   public static void mergeSort(int A[], int p, int r) {
      if (p < r) {
         int q = (p + r) / 2;
         
         mergeSort(A, p, q);
         mergeSort(A, q+1, r);
         merge(A, p, q, r);
      }
   }   
   
   //Generates a random array 
   public static int[] generateRandomArray(int n, int MAX_VALUE){
      int out[] = new int[n];
   
      Random random = new Random();
      for (int i = 0; i < out.length; ++i){
         out[i] = random.nextInt(MAX_VALUE);
      }
      return out;
   }

   public static void dataRun(){
      Scanner stdin = new Scanner(System.in);
      String FILENAME = "M5Output.csv";
      OutputStream outputStream;
      try {
         outputStream = new FileOutputStream(new File(FILENAME));
      }
      catch (FileNotFoundException e){
         e.printStackTrace();
         stdin.close();
         return;
      }
   
      System.out.print("Enter the size of the largest array to sort (max 2^31): ");
      int MAX_VALUE = stdin.nextInt();
      int largeRandom[] = generateRandomArray(MAX_VALUE, Integer.MAX_VALUE);
      String result = "n, TNaiveSort(n), TMergeSort(n)\n";
      for (int j = 4000; j <= MAX_VALUE; j+=1000 ){
         int currentProblem[] = new int[j];
         for (int i = 0; i < currentProblem.length; i++){
            currentProblem[i] = largeRandom[i];
         }
         NumberFormat formatter = new DecimalFormat("#0.00000");
         
         
         long start = System.currentTimeMillis();//starts time for mergesort
         M5Program.mergeSort(currentProblem, 0, currentProblem.length-1);
         long end = System.currentTimeMillis();//ends time for mergesort
         long MergeTime = (end-start);
         
         long start2 = System.currentTimeMillis();//starts time for naive sort
         M5Program.NaiveSort(currentProblem);
         long end2 = System.currentTimeMillis();//ends time for naive sort
         long NaiveTime = (end2-start2);
         
         result +=  j + ", " + formatter.format((NaiveTime) / 1000d) + "," + formatter.format((MergeTime) / 1000d) + "\n";
      }
      
      try {
         outputStream.write(result.getBytes());
         outputStream.close();
      } catch (IOException e) {
         e.printStackTrace();
         stdin.close();
         return;
      }
      stdin.close();
   }
   
   

   public static void main(String[] args) {
      dataRun();
   }
}