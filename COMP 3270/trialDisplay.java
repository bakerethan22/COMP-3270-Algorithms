import java.util.Scanner;

import java.util.*;

import java.text.*;

class Main

{

   void mergetech(int numarr[], int l, int m, int r)
   
   {
   
   //variable declaration
   
      int t1 = m - l + 1;
   
      int t2 = r - m;
   
      int leftarr[] = new int [t1];
   
      int rightarr[] = new int [t2];
   
      for (int i=0; i<t1; ++i)
      
         leftarr[i] = numarr[l + i];
   
      for (int j=0; j<t2; ++j)
      
         rightarr[j] = numarr[m + 1+ j];
   
      int i = 0, j = 0;
   
      int k = l;
   
      while (i < t1 && j < t2)
      
      {
      
         if (leftarr[i] <= rightarr[j])
         
         {
         
            numarr[k] = leftarr[i];
         
            i++;
         
         }
         
         else
         
         {
         
            numarr[k] = rightarr[j];
         
            j++;
         
         }
      
         k++;
      
      }
   
      while (i < t1)
      
      {
      
         numarr[k] = leftarr[i];
      
         i++;
      
         k++;
      
      }
   
      while (j < t2)
      
      {
      
         numarr[k] = rightarr[j];
      
         j++;
      
         k++;
      
      }
   
   }

//sorting method

   void sorting(int numarr[], int l, int r)
   
   {
   
      if (l < r)
      
      {
      
         int m = (l+r)/2;
      
         sorting(numarr, l, m); //invoking the function
      
         sorting(numarr , m+1, r);
      
         mergetech(numarr, l, m, r);
      
      }
   
   }

   static void display(int numarr[]) //display fuction
   
   {
   
      int n = numarr.length;
   
      for (int i=0; i<n; ++i) //displaying the values
      
         System.out.print(numarr[i] + " ");
   
      System.out.println();
   
   }

   public static void main(String args[]) //main function
   
   {
   
      Scanner sc=new Scanner(System.in);
   
      int i,n;
   
   //start of execution time
   
      long start = System.currentTimeMillis();
   
   //accepting the length of the array
   
      System.out.println("Enter the length of the array");
   
      n=sc.nextInt();
   
      int numarr[]= new int[n];
   
   //accepting the elements of the array
   
      System.out.println("Enter the elements of the array");
   
      for(i=0;i<n;i++)
      
      {
      
         numarr[i]=sc.nextInt();
      
      }
   
      System.out.println("Before Sorting");
   
      System.out.println("Input Array");
   
      display(numarr);
   
      Main ob = new Main(); //object of the array
   
      ob.sorting(numarr, 0, n-1); //calling the sorting function
   
   //end of execution time
   
      long end = System.currentTimeMillis();
   
      System.out.println("After Sorting");
   
      System.out.println(" Output array");
   
      display(numarr);
   
      NumberFormat formatter = new DecimalFormat("#0.00000");
   
   //displaying the execution time
   
      System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
   
   }
}
