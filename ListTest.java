
// -------------------------------------------------------------------
// Leena Kahlon
// lkahlon
// ListTest.java
// 11-5-17
// -------------------------------------------------------------------

public class ListTest{
   public static void main(String[] args){
      List A = new List();
      List B = new List();

      for(int i = 1; i<=10; i++){
         A.append(i);
         B.prepend(i);
      }
      A.append(5.0);
      A.append(17.0);
      A.append(13.0);
      A.append(3.0);
      A.append(7.0);

      B.prepend(5.0);
      B.prepend(17.0);
      B.prepend(13.0);
      B.prepend(3.0);
      B.prepend(7.0);
      System.out.println(A);
      System.out.println(B);
      System.out.println();

      for(A.moveFront(); A.index()>=0; A.moveNext()){
         System.out.print(A.get()+" ");
      }
      System.out.println();
      for(B.moveBack(); B.index()>=0; B.movePrev()){
         System.out.print(B.get()+" ");
      }
      System.out.println();
      
      A.moveFront();      
      for(int i = 0; i < 4; i++) A.moveNext();
      A.insertBefore(6.0); 
      A.moveBack();
      for(int i = 0; i < 6; i++) A.movePrev();
      A.insertAfter(4.0);
      
      B.moveBack();
      for(int i = 0; i < 4; i++) B.movePrev();
      A.insertAfter(4.0);
      B.moveFront();
      for(int i = 0; i < 3; i++) B.moveNext();
      B.insertBefore(16.0);

      System.out.println();
      System.out.println(A);
      System.out.println(B);
      
      System.out.println(A.equals(B));
      A.moveFront();
      System.out.println(A.index());
      System.out.println(A.front());
      System.out.println(A.back());

      A.deleteFront();
      A.deleteBack();
      System.out.println();
      System.out.println(A);
      System.out.println(); 

      B.clear();
      System.out.println(B.length());
             
      A.moveFront();
      A.moveNext();
      A.delete();
      System.out.println(A);
      System.out.println(A.length());
   }
}

