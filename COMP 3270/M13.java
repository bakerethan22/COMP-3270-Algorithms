import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

public class M13 {
  
   public static List<Integer> recursiveActivitySelector(int[] start, int[] finish, int subIndex, int activities, List<Integer> A) {
      int m = subIndex + 1;
      while (m <= activities && start[m] < finish[subIndex]) { 
         m = m + 1;
      }
      if (m <= activities) {
         A.add(m);
         recursiveActivitySelector(start, finish, m, activities, A);
         return A;
      }
      else {
         return null;
      }
   }

   public static List<Integer> greedyActivitySelector(int[] start, int[] finish) {
      List<Integer> A = new ArrayList<>();
      A.add(1);
      int activities = start.length;
      int subIndex = 1;
      for (int m = 2; m < activities; m++) {
         if (start[m] >= finish[subIndex]) {
            A.add(m);
            subIndex = m;
         }
      }
      return A;
   }
   
   //collects data for plots
   public static void studyOverhead(int numberPoints, int activities) {
      int[] start = new int[activities];
      int[] finish = new int[activities];
      initializeArrays(activities, start, finish);
   
      for (int i = 1; i < numberPoints; i++) {
         long timeForRecursive = 0;
         long timeForIterative = 0;
         for (int j = 1; j <= numberPoints; j++) {
            List<Integer> A = new ArrayList<>();
            long recursiveStartTime;
            long recursiveFinishTime;
            long iterativeStartTime;
            long iterativeFinishTime;
         
            recursiveStartTime = System.nanoTime();
            recursiveActivitySelector(start, finish, 0, activities - 1, A);
            recursiveFinishTime = System.nanoTime();
            timeForRecursive += recursiveFinishTime - recursiveStartTime;
         
            iterativeStartTime = System.nanoTime();
            greedyActivitySelector(start, finish);
            iterativeFinishTime = System.nanoTime();
            timeForIterative += iterativeFinishTime - iterativeStartTime;
         }
         System.out.println(i + ", " + (double) timeForRecursive / (double) timeForIterative);
      }
   }
   
   
   public static void initializeArrays(int activities, int[] start, int[] finish) {
      for (int i = 1; i < activities - 1; i++) {
         if (i % 2 == 0) { 
            start[i] = finish[i-2];
            finish[i] = start[i] + 2;
         }
         else {
            start[i] = finish[i-1] - 1;
            finish[i] = finish[i-1] + 1;
         }
      }
   }
   
   
   public static void main(String[] args) throws FileNotFoundException {
      Scanner activityInput = new Scanner(System.in);
      System.out.println("Enter a number of activities: ");
      int activities = activityInput.nextInt();
      System.out.println("First Column is i, Second Column is M[i]");
      PrintStream O = new PrintStream(new File("M13Output.csv"));
      PrintStream console = System.out;
      System.setOut(O);
      studyOverhead(activities, activities);
      System.setOut(console);
      System.out.println("Check M13Ouput.csv for the values.");
         
   }
}