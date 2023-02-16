import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class M9 {
  
   // class Node used by tree
   public static class Node {
      
       // attributes
      int key;
      Node left;
      Node right;
      
       // constructor
      Node(int key) {
         this.key = key;
         this.left = null;
         this.right = null;
      }
   }
  
   // class Tree
   public static class Tree {
      
       // attributes
      Node root = null;
      
       // constructors
      Tree(Node root) {
         this.root = root;
      }
      
      Tree() {
          
      }
   }
  
   // function to add node in a tree.
   public static void addNode(Tree tree, Node z) {
      
       // if root id null, make new node as root.
      if(tree.root == null) {
         tree.root = z;
         return;
      }
      
       // y is previous node, x is current node
      Node y = null;
      Node x = tree.root;
      
       // go left and right according to the BST property and node value.
      while(x != null) {
         y = x;
         if(z.key <= x.key) {
            x = x.left;
         } else {
            x = x.right;
         }
      }
      
       // add the new node.
      if(z.key <= y.key) {
         y.left = z;
      } else {
         y.right = z;
      }
   }
  
   // function to get height of BST
   public static int getHeight(Node node) {
       // height is 0 is node is null.
      if(node == null) {
         return 0;
      }
      
       // get height from left and right sub tree.
      int leftHeight = getHeight(node.left);
      int rightHeight = getHeight(node.right);
      
       // select max and add 1 for the current node.
      return Math.max(leftHeight, rightHeight) + 1;
   }
  
  
   // function to collect data
   public static ArrayList<Integer> CollectData() {
      
       // arraylist stores height of trees of different sizes.
       // index i will store height for a tree of size (i+1) * 250.
      ArrayList<Integer> heights = new ArrayList<>();
      
       // for trees of several different sizes
      for(int n = 250; n <= 10000; n = n + 250) {
          
           // construct 10 trees of size n
           // get average height for 10 trees.
         int heightSum = 0;
         for(int i = 0; i < 10; i++) {
            Tree tree = new Tree();
            for(int j = 0; j < n; j++) {
                   // create object of type Random to generate random numbers.
               Random rand = new Random();
               int key = rand.nextInt() % n;
               Node z = new Node(key);
               addNode(tree, z);
            }
              
               // get height of tree.
            int height = getHeight(tree.root);
            heightSum = heightSum + height;
         }
          
           // calculate average and store the data.
         int avgHeight = heightSum / 10;
         heights.add(avgHeight);
      }
      
       // return the data.
      return heights;
   }

   public static void main(String[] args) {
       // call data function.
      ArrayList<Integer> data = CollectData();
      
       // write data into a text file.
      try {
         FileWriter myWriter = new FileWriter("TreeData.csv");
         for(int i = 0; i < data.size(); i++) {
            myWriter.write(((i+1)*250) + " " + data.get(i));
            myWriter.write("\n");
         }
         myWriter.close();
      } catch (IOException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   }

}