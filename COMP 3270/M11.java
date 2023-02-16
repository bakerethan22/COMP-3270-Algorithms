import java.util.*;
import java.lang.*;
import java.io.*;

//We used GeekforGeeks on how to implement a simple Bellman-Ford algorithm.

public class M11 {
   
   class Edge {
      int src, dest, weight;
   
      Edge(int src, int dest, int weight) {
         this.src = src;
         this.dest = dest;
         this.weight = weight;
      }
   }

   ;

   int verticesCount, edgeCount;
   ArrayList<Edge> edgeList;

   M11() {
      verticesCount = 0;
      edgeCount = 0;
      edgeList = new ArrayList<Edge>();
   }

   void addEdge(int src, int dest, int weight) {
      edgeList.add(new Edge(src, dest, weight));
      edgeCount++;
   }

  
   String printEdges() {
      String s = "";
      for (int i = 0; i < edgeCount; i++) {
         s += edgeList.get(i).src + " " + edgeList.get(i).dest + " " + edgeList.get(i).weight + "\n";
      }
      return s;
   }

   //BellmanFord Algorithm
   String BellmanFord(M11 graph, int src) {
      int V = graph.verticesCount, E = graph.edgeCount;
      int dist[] = new int[V];
      int parents[] = new int[V];
   
      //initialize distances
      for (int i = 0; i < V; ++i) {
         dist[i] = Integer.MAX_VALUE;
         parents[i] = -1;
      }
      dist[src] = 0;
      parents[src] = src;
   
      //relax edges
      for (int i = 1; i < V; ++i) {
         for (int j = 0; j < E; ++j) {
            int u = graph.edgeList.get(j).src;
            int v = graph.edgeList.get(j).dest;
            int weight = graph.edgeList.get(j).weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
               dist[v] = dist[u] + weight;
               parents[v] = u;
            }
         }
      }
   
      //check for negative weight cycles
      for (int j = 0; j < E; ++j) {
         int u = graph.edgeList.get(j).src;
         int v = graph.edgeList.get(j).dest;
         int weight = graph.edgeList.get(j).weight;
         if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
            System.out.println("Graph contains negative weight cycle");
            return "Negative Cycles Found.";
         }
      }
   
      String output = "";
      for (int i = 0; i < V; ++i) {
         if (i == src) {
            continue;
         }
         output += i + ": " + getPath(i, src, parents) + "\n";
      }
      return output;
   }

   // get path to src
   String getPath(int src, int dest, int[] parents) {
   
      // follow parents to get path from a dest to src
      String path = "";
      int u = src;
      while (u != dest) {
         path += u + " ";
         u = parents[u];
         if (u == -1) {
            return "No Path Found.";
         }
      }
      // reverse path to get from src to dest
      String[] pathArr = path.split(" ");
      String reversePath = "";
      for (int i = pathArr.length - 1; i >= 0; i--) {
         reversePath += pathArr[i] + " ";
      }
   
      return reversePath;
   }

   
   public static void main(String[] args) {
     
      System.out.print("Input File name: ");
      Scanner input = new Scanner(System.in);
      try {
         File file = new File(input.nextLine());
         input = new Scanner(file);
      } catch (NullPointerException e) {
         input.close();
         System.out.println("File not found.");
         System.exit(0);
      } catch (FileNotFoundException e) {
         input.close();
         System.out.println("File not found.");
         System.exit(0);
      }
      M11 graph = new M11();
   
      // Make first value source
      int src = Integer.parseInt(input.nextLine());
   
      //reads rest of values line by line 
      while (input.hasNextLine()) {
         String line = input.nextLine();
         graph.verticesCount++;
      
         String[] restOfData = line.split(" ");
         int origin = Integer.parseInt(restOfData[0]);
      
         for (int i = 1; i < restOfData.length; i++) {
            String[] currentData = restOfData[i].split(",");
            int destination = Integer.parseInt(currentData[0]);
            int weight = Integer.parseInt(currentData[1]);
            graph.addEdge(origin, destination, weight);
         }
      }
      input.close();
      String result = graph.BellmanFord(graph, src);
      System.out.println(result);
   
   
      File output = new File("outputShortestPaths.txt");
      OutputStream outputStream;
      try {
         outputStream = new FileOutputStream(output);
         outputStream.write(result.getBytes());
         outputStream.close();
      } catch (Exception e) {
         System.out.println("Error writing to file.");
         System.exit(0);
      }
   
   }
}