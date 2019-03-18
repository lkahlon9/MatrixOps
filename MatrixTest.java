
// -------------------------------------------------------------------
// Leena Kahlon
// lkahlon
// MatrixTest.java
// 11-5-17
// -------------------------------------------------------------------

class MatrixTest{
   public static void main(String[] args){
      Matrix A = new Matrix(5);
      Matrix B = new Matrix(5);

      A.changeEntry(1, 2, 1);
      A.changeEntry(4, 2, 19);
      A.changeEntry(2, 3, 6);
      A.changeEntry(5, 5, 20);
      A.changeEntry(3, 3, 10);

      A.changeEntry(3, 2, 14);
      A.changeEntry(5, 3, 10);
      A.changeEntry(4, 1, 10);
      A.changeEntry(3, 4, 3);
      A.changeEntry(4, 4, 12);

      A.changeEntry(2, 5, 12);
      A.changeEntry(1, 1, 14);
      A.changeEntry(1, 4, 17);
      A.changeEntry(3, 5, 15);
      A.changeEntry(2, 4, 4);

      A.changeEntry(1, 3, 6);
      A.changeEntry(5, 2, 8);
      A.changeEntry(2, 1, 20);
     
      System.out.println(A.getSize());
      System.out.println(A.getNNZ());
      System.out.println(A); 

      B = A.copy();
      System.out.println(B.getSize());
      System.out.println(B.getNNZ());
      System.out.print(B);

      System.out.println(A.equals(B));

      B = B.scalarMult(3);
      System.out.print(B);
      System.out.println(A.equals(B));
      System.out.println();

      Matrix C = new Matrix(5);
      C = A.add(B);      
      System.out.println(C);
      C = A.sub(B);
      System.out.print(C);
      System.out.println(C.getNNZ());

      Matrix D = new Matrix(5);
      D = C.transpose();
      System.out.print(D);
      System.out.println(D.getSize());
      System.out.println(D.getNNZ());
      System.out.println(C);      

      Matrix E = new Matrix(5);
      E.changeEntry(1, 1, -6);
      E.changeEntry(1, 2, -8);
      E.changeEntry(1, 4, -8);
      E.changeEntry(1, 5, -12);

      E.changeEntry(2, 1, -10);
      E.changeEntry(2, 3, -16);
      E.changeEntry(2, 4, -4);
      E.changeEntry(2, 5, -13);

      E.changeEntry(3, 2, -20);
      E.changeEntry(3, 5, -9);

      E.changeEntry(4, 1, -15);
      E.changeEntry(4, 2, -2);
      E.changeEntry(4, 3, -18);
      E.changeEntry(4, 4, -1);
      E.changeEntry(4, 5, -11);

      E.changeEntry(5, 1, -9);
      E.changeEntry(5, 2, -20);
      E.changeEntry(5, 4, -13);

      E = C.mult(E);
      System.out.println();
      System.out.print(E);
      System.out.println(E.getSize());
      System.out.println(E.getNNZ());

      E.changeEntry(1, 1, 10);
      E.changeEntry(2, 2, 20);
      E.changeEntry(3, 3, 30);
      E.changeEntry(4, 4, 40);
      E.changeEntry(5, 5, 50);
      System.out.println();
      System.out.print(E);
      System.out.println(E.getNNZ());
   }
} 

