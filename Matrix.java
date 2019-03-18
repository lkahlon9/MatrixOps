
// -------------------------------------------------------------------
// Leena Kahlon
// lkahlon
// Matrix.java
// 11-5-17
// -------------------------------------------------------------------

class Matrix{

   private class Entry{
     
      int column;
      double value;
      
      Entry(int column, double value) { 
         this.column = column;
         this.value = value;
      }

      public String toString() {
         StringBuffer sb = new StringBuffer();
         sb.append(this.column);
         sb.append(", ");
         sb.append(this.value);
         return new String(sb);
      }

      public boolean equals(Object x) {
         boolean eq = false;
         Entry A;
         if( x instanceof Entry ) {
            A = (Entry) x;
            eq = (this.column == A.column);
            if(eq == true) eq = (this.value == A.value);   
         }
         return eq;       
      }
   }

   private int size;
   private int NNZ;
   private List[] row;

   Matrix(int n) {
      if( n < 1 ) {
         throw new RuntimeException(
            "Matrix Error: Matrix() called with n < 1");
      }
      row = new List[n];
      for( int i = 1; i <= n; i++ ){
         row[i-1] = new List();      
      }
      size = n;
      NNZ = 0;
   }

   // Access Functions -----------------------------------------------

   int getSize() {
      return size;
   }

   int getNNZ() {
      return NNZ;      
   }

   public boolean equals(Object x) {
      boolean eq = false;
      Matrix M;
      List A, B;

      if( x instanceof Matrix ){
         M = (Matrix)x;
         eq = (this.size == M.size);
         int i = 1;
         while( eq && (i <= this.size) ){
            A = this.row[i-1]; 
            B = M.row[i-1];
            eq = A.equals(B);
            i++;
         }          
      }   
      return eq;
   }   

   // Manipulation Procedures ----------------------------------------

   void makeZero() {
      for( int i = 1; i <= this.size; i++ ){ 
         row[i-1] = null;
      }
      NNZ = 0;
      for( int i = 1; i <= this.size; i++ ){
         row[i-1] = new List();
      }         
   }
   
  
   Matrix copy() {
      Matrix M = new Matrix(this.size);

      for( int i = 1; i <= this.size; i++ ){
         M.row[i-1] = this.row[i-1];
      }
      M.NNZ = this.NNZ;
      M.size = this.size;
      return M;
   }
       
   void changeEntry(int i, int j, double x) {   
      if( i < 1 || i > getSize() ){
         throw new RuntimeException(
            "Matrix Error: changeEntry() pre: 1 <= i <= size" ); 
      }
      if( j < 1 || j > getSize() ){ 
         throw new RuntimeException(
            "Matrix Error: changeEntry() pre: 1 <= j <= size" );
      }

      List A = new List();
      A = this.row[i-1]; 
      
      Entry Z = new Entry(j, x);
   
      if( A.length() == 0 ){
         if( x != 0 ){ 
            A.append(Z);
            NNZ++;
         }
      } 

      if( A.length() > 0 ){
        
         A.moveFront();
         Entry X = (Entry)A.get();

         while( A.index() >= 0 && X.column < j ){
            A.moveNext();
            if( A.index() >= 0 )
              X = (Entry)A.get();
         }

         if( A.index() >= 0 && X.column == j ){
            if( x != 0 ) 
               X.value = x;
            else if( x == 0 ) {
               A.delete();
               NNZ--;
            }
         } 
         
         else if( A.index() >= 0 && X.column != j ){
            if( x != 0 ){
               A.insertBefore(Z);
               NNZ++;
            }
         }
         
         else if( A.index() < 0 ){
            if( x != 0 ){
               A.append(Z);
               NNZ++;
            }   
         }
      }
   } 

   Matrix scalarMult(double x) {
      Matrix M = new Matrix(this.size);
      
      for( int i = 1; i <= this.size; i++ ){
         List A = M.row[i-1];
         List B = this.row[i-1];
         
         B.moveFront();
         if( B.length() > 0 ){
            while( B.index() >= 0 ){
               Entry Y = (Entry)B.get();
               int a = Y.column; double b = Y.value*x;
               Entry Z = new Entry(a, b);
               A.append(Z);
               B.moveNext();
            }
         }
      }
      M.NNZ = this.NNZ;
      return M;            
   }
   
   Matrix add(Matrix M) {
      if( getSize() != M.getSize() ){
         throw new RuntimeException(
            "Matrix Error: add() called while this.size != M.size" );
      }
      
      Matrix N = new Matrix(this.size);
      Matrix E = new Matrix(this.size);
       
      for( int i = 1; i <= this.size; i++ ){
         List A = M.row[i-1];
         List B = this.row[i-1];
         List C = N.row[i-1];
    
         A.moveFront();
         B.moveFront();
         Entry X = new Entry(0, 0);
         Entry Y = new Entry(0, 0);

         for( int j = 1; j <= this.size; j++ ){
            if( A.index() >= 0 ) 
               X = (Entry)A.get();
            if( B.index() >= 0 )
               Y = (Entry)B.get();
            
            boolean s = false; boolean t = false;
            int q = 0; int r = 0;
            
            while( A.index() >= 0 ){
               if( X.column == j ){ 
                  X = (Entry)A.get();
                  s = true;
                  A.moveBack();
               }
               A.moveNext();
               if( A.index() >= 0 ) X = (Entry)A.get(); 
               q++;
            }  

            while( B.index() >= 0 ){       
               if( Y.column == j ){
                  Y = (Entry)B.get();
                  t = true;
                  B.moveBack();
               }
               B.moveNext();
               if( B.index() >= 0 ) Y = (Entry)B.get();
               r++;
            }

            A.moveFront();
            B.moveFront(); 

            if( s != true ) A.movePrev();
            if( t != true ) B.movePrev();
                      
            if( A.index() >= 0 && B.index() >= 0 ){
               A.moveFront();
               B.moveFront();
               
               q = q - 1;
               r = r - 1;
               while( (A.index() >= 0) && (q != 0) ){
                  A.moveNext();
                  q--;
               }
               while( (B.index() >= 0) && (r != 0) ){
                  B.moveNext();
                  r--;
               }
               if( (A.index() >= 0) && (B.index() >= 0) ){
                  X = (Entry)A.get();
                  Y = (Entry)B.get();
                  double sum = X.value + Y.value; 
                  Entry Z = new Entry(j, sum);
                  C.append(Z);  
                  if(sum != 0) N.NNZ++;
               }
            }
            
            else if( A.index() >= 0 && B.index() < 0 ){
               A.moveFront();
               q = q - 1;
               while( A.get() != null && q != 0 ){
                  A.moveNext();
                  q--;
               } 
               if( A.get() != null ){
                  X = (Entry)A.get();
                  Entry Z = new Entry(j, X.value);
                  C.append(Z);
                  N.NNZ++;                 
               }
            }

            else if(A.index() < 0 && B.index() >= 0 ){
               B.moveFront(); 
               r = r - 1;
               while( B.get() != null && r != 0 ){
                  B.moveNext();
                  r--;
               }
               if( B.get() != null ){                  
                  Y = (Entry)B.get();
                  Entry Z = new Entry(j, Y.value);
                  C.append(Z);
                  N.NNZ++;
               }
            }

            A.moveFront();
            B.moveFront();
         
         }
         
      }

      for( int i = 1; i <= this.size; i++ ){
         E.row[i-1] = this.row[i-1];
      }
      if( M.equals(E) == false ) return N;   
      else return ( M.scalarMult(2.0) );      
   }
   
   Matrix sub(Matrix M) {
      if( getSize() != M.getSize() ){
         throw new RuntimeException(
            "Matrix Error: sub() called while this.size != M.size");
      }
      
      Matrix N = new Matrix(this.size);
      
      for( int i = 1; i <= this.size; i++ ){
         List A = M.row[i-1];
         List B = this.row[i-1];
         List C = N.row[i-1];
   
         A.moveFront();
         B.moveFront();
         Entry X = new Entry(0, 0);
         Entry Y = new Entry(0, 0);
         for( int j = 1; j <= this.size; j++ ){
            if( A.index() >= 0 )
               X = (Entry)A.get();
            if( B.index() >= 0 )
               Y = (Entry)B.get();
            
            boolean s = false; boolean t = false;
            int q = 0; int r = 0;

            while( A.index() >= 0 ){
               if( X.column == j ){
                  X = (Entry)A.get();
                  s = true;
                  A.moveBack();
               }
               A.moveNext();
               if( A.index() >= 0 ) X = (Entry)A.get();
               q++;
            }

            while( B.index() >= 0 ){
               if( Y.column == j ){
                  Y = (Entry)B.get();
                  t = true;
                  B.moveBack();
               }
               B.moveNext();
               if( B.index() >= 0 ) Y = (Entry)B.get();
               r++;
            }
            
            A.moveFront();
            B.moveFront();

            if( s != true ) A.movePrev();
            if( t != true ) B.movePrev();
            
            if( A.index() >= 0 && B.index() >= 0 ){
               A.moveFront();
               B.moveFront();
               
               q = q - 1;
               r = r - 1;
               while( A.get() != null && q != 0 ){
                  A.moveNext();
                  q--;
               }
               while( B.get() != null && r != 0 ){
                  B.moveNext();
                  r--;
               }
               if( A.get() != null && B.get() != null ){
                  X = (Entry)A.get();
                  Y = (Entry)B.get();
                  double dif = Y.value - X.value;
                  if( dif != 0 ) {
                     Entry Z = new Entry(j, dif);
                     C.append(Z);
                     N.NNZ++;
                  }
               }
            }
            
            else if( A.index() >= 0 && B.index() < 0 ){
               A.moveFront();
               q = q - 1;
               while( A.get() != null && q != 0 ){
                  A.moveNext();
                  q--;
               }
               if( A.get() != null ){
                  X = (Entry)A.get();
                  Entry Z = new Entry(j, -(X.value));
                  C.append(Z);
                  N.NNZ++;
               }
            }
           
            else if( A.index() < 0 && B.index() >= 0 ){
               B.moveFront();
               r = r - 1;
               while( B.get() != null && r != 0 ){
                  B.moveNext();
                  r--;
               }
               if( B.get() != null ){
                  Y = (Entry)B.get();
                  Entry Z = new Entry(j, Y.value);
                  C.append(Z);
                  N.NNZ++;
               }
            }
            
            A.moveFront();
            B.moveFront();
         }
      }
      return N;
   } 

   Matrix transpose() {
      
      Matrix M = new Matrix(this.size);
      int a; 
      double b;
      for( int i = 1; i <= this.size; i++ ){
         List A = this.row[i-1];
         A.moveFront();   
         for( int j = 1; j <= this.size; j++ ){
            if( A.index() >= 0 ){
               Entry X = (Entry)A.get();
               List B = M.row[j-1];
               if( X.column == j ){
                  Entry Y = new Entry(0, 0);
                  Y.column = i;
                  b = X.value;
                  Y.value = b;
                  B.append(Y);
               } else {
                  if( X.column > j ){
                     Entry Y = new Entry(0, 0);
                     a = X.column;
                     Y.column = i;
                     b = X.value;
                     Y.value = b;
                     B = M.row[a-1];
                     B.append(Y);
                  }
               }
               A.moveNext();
            }
         }        
      }

      M.NNZ = this.NNZ;
      return M;
   }

   Matrix mult(Matrix M) {
      Matrix N = new Matrix(this.size);
      double product = 0;
      double finproduct = 0;

      for( int k = 1; k <= this.size; k++ ){
      
      for( int i = 1; i <= this.size; i++ ){
      
         List A = this.row[i-1];
         finproduct = 0;
         Entry X = new Entry(0, 0);
         Entry Y = new Entry(0, 0);
         for( int j = 1; j <= this.size; j++ ){
            A.moveFront(); 
            if( A.index() >= 0 ) X = (Entry)A.get();

            for( int a = 1; a <= this.size; a++ ){
               if( X.column < j ){ 
                  A.moveNext();
                  if( A.index() >= 0 ){
                     X = (Entry)A.get();
                  }
               }
            } 
            List B = M.row[j-1];
                  
            B.moveFront();
            if( B.index() >= 0 ) Y = (Entry)B.get();
            for( int b = 1; b <= this.size; b++ ){
               if( Y.column < k ){
                  B.moveNext();
                  if( B.index() >= 0 ) 
                     Y = (Entry)B.get();
               }
            }     
            
            if( A.index() >= 0 && B.index() >= 0 ){
               if( X.column == j && Y.column == k ) product = X.value*Y.value;
               else product = 0;
            }
            else product = 0;
            finproduct = finproduct + product;            
         }

         List C = N.row[i-1];
         Entry Z = new Entry(k, finproduct);
         if( finproduct != 0 ){ 
            C.append(Z);
            N.NNZ++;
         }
      }

      }   
      return N;
   }

   // Other functions ------------------------------------------------

   public String toString() {
      StringBuffer sb = new StringBuffer();
         for( int i = 1; i <= this.size; i++ ){
            List L = row[i-1];
            if( L.length() > 0 ){
               sb.append(i);
               sb.append(": ");
               L.moveFront();
               Entry X = (Entry)L.get();
           
               while(L.index() >= 0 ){
                  X = (Entry)L.get();
                  sb.append("(");
                  sb.append(X.toString());
                  sb.append(")");
                  if(L.index() != L.length()) sb.append(" ");   
                  L.moveNext();
               }
               sb.append("\n");
            }   
         }
      return new String(sb);    
   }
      
}      
      

 
  
