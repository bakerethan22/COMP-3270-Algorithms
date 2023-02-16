import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.lang.*;
import java.io.FileWriter; 
import java.io.IOException;
/**
*M1 programming assignment
*Comp 3270
*Ethan Baker & Austin Dickinson
*
**/
public class M1 {

   private static final double SECONDS = 1_000_000_000d;
   //Not sure if we just needed the method or if we needed to make a main function as well
   
   public static double computeSumPowers(double x, double n) { //Algorithm A
      double sum = 0;
      for(int i = 1; i <= n; i++) {
         double prod = 1;
         for(int j = 1; j <= i; j++) {
            prod *= x;
         }
         sum += prod;
      }
      return sum;
   }
   
   public static double collectData() { //Calculates execution time
      long start = 0;
      long elapsed = 0;
      double diff = 0;
      int n;
      
      for (n = 100; n <= 25000; n += 100) {
      
         start = System.nanoTime();
         computeSumPowers(0.25,n);
         elapsed = (System.nanoTime() - start);
         diff = (elapsed / SECONDS);
         
      
      }
      
      double root = (diff / Math.sqrt(n));
      double expo = (diff / Math.pow(n, 2));
      double log = (diff / n * Math.log(n));
   
      try {
         FileWriter writer = new FileWriter("M1.csv");
         List<String[]> header = new ArrayList<String[]>();
         header.add(new String[] {"T(n)", "T(n)/sqrt(n)", "T(n)/n^2", "T(n)/nln(n)"});
         writer.write(header.toString());
         List<double[]> data = new ArrayList<double[]>();
         data.add(new double[] {diff, root, expo, log});
         writer.write(data.toString());
         writer.close();
         System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
      
      return diff;
   }


   public static void main(String [] args) { //Tests computeSumPowers
      double x = 0;
      double n = 0;
      Scanner inputX = new Scanner(System.in);
      System.out.print("Input x: ");
      x = inputX.nextDouble();
      while (x < 0 || x > 1) {
      
         System.out.print("X cannot be less than 0 or greater than 1. Input x: ");
         x = inputX.nextDouble();
         
      }
      
      Scanner inputN = new Scanner(System.in);
      System.out.print("Input n: ");
      n = inputN.nextDouble();
      
      while (n < 0) {
         
         System.out.print("N cannot be less than 0. Input n: ");
         n = inputN.nextDouble();
      }
      double total = computeSumPowers(x, n);
      System.out.println(total);
      System.out.println("Elapsed time: " + collectData() + " seconds.");
   }

}
