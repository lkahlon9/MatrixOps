// -------------------------------------------------------------------
// Leena Kahlon
// lkahlon
// Sparse.java
// 11-5-17
// -------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Sparse{

   public static void main(String[] args) throws IOException{

      Scanner in = null;
      PrintWriter out = null;
      int n, a, b = 0;
      int row, column = 0;
      String line = null;
      String[] token = null;
      double value = 0;

      if( args.length != 2 ) {
         System.err.println("Usage: Sparse infile outfile");
         System.exit(1);
      }

      in = new Scanner(new File (args[0]));
      out = new PrintWriter(new FileWriter(args[1]));
      
      line = in.nextLine()+" ";
      token = line.split("\\s+");
      n = Integer.parseInt(token[0]);
      a = Integer.parseInt(token[1]);
      b = Integer.parseInt(token[2]);
      
      Matrix A = new Matrix(n);
      Matrix B = new Matrix(n);

      line = in.nextLine()+" ";
      for( int i = 1; i <= a; i++ ) {
         line = in.nextLine()+" ";
         token = line.split("\\s+");
         row = Integer.parseInt(token[0]);
         column = Integer.parseInt(token[1]);
         value = Double.parseDouble(token[2]);
         A.changeEntry(row, column, value);
      }

      line = in.nextLine()+" ";
      for( int i = 1; i <= b; i++ ) {
         line = in.nextLine()+" ";
         token = line.split("\\s+");
         row = Integer.parseInt(token[0]);
         column = Integer.parseInt(token[1]);
         value = Double.parseDouble(token[2]);
         B.changeEntry(row, column, value);
      }

      int x, y = 0;    
      x = A.getNNZ();
      y = B.getNNZ();
      out.println("A has " + x + " non-zero entries:");
      out.println(A);
      out.println("B has " + y + " non-zero entries:");
      out.println(B);
      
      out.println("(1.5)*A = ");
      out.println(A.scalarMult(1.5));

      out.println("A+B = ");
      out.println(A.add(B));
      
      out.println("A+A = ");
      out.println(A.add(A));

      out.println("B-A = ");
      out.println(B.sub(A));

      out.println("A-A = ");
      out.println(A.sub(A));
      
      out.println("Transpose(A) = ");
      out.println(A.transpose());

      out.println("A*B = ");
      out.println(A.mult(B));

      out.println("B*B = ");
      out.println(B.mult(B));  
                                 
      in.close();
      out.close();
   
   }
}
         
