
// -----------------------------------------------
// Leena Kahlon
// lkahlon
// List.java
// 11-5-17
// -----------------------------------------------

class List{

   private class Node{
   
      Object data;
      Node prev;
      Node next;

      // Node Constructor
      Node(Object data){
         this.data = data;
         prev = null;
         next = null;
      }

      // Node toString()
      public String toString(){
         return String.valueOf(data);
      }
   }

   private Node front;
   private Node back;
   private Node cursor;
   private int length;
   private int index;

   // List constructor
   List() {
      front = back = null;
      cursor = null;
      length = 0;
      index = -1;
   }

   // Access Functions ----------------------------------------------

   int length() {
      return length;
   }

   int index() {
      return index;
   }

   Object front() {
      if( length <= 0 ){
         throw new RuntimeException(
            "List Error: front() called on empty List");
      }
      return front.data;
   }

   Object back() {
      if( length <= 0 ){
         throw new RuntimeException(
            "List Error: back() called on empty List");
      }
      return back.data;
   }
   
   Object get() {
      if( length <= 0 ){
         throw new RuntimeException(
            "List Error: get() called on empty List");
      }
      if( index < 0 ){
         throw new RuntimeException(
            "List Error: get() called with undefined cursor");
      }
      
      return cursor.data;
      
   }

   public boolean equals(Object x) {
      boolean eq = false;
      List L;
      Node N, M;
     
      if(x instanceof List){
         L = (List)x;
         N = this.front;
         M = L.front;
         eq = (this.length == L.length);
         while( eq && N != null ){
            eq = (N.data == M.data); 
            N = N.next;
            M = M.next;
         }
      }      
      return eq;
   }

   // Manipulation Procedures ----------------------------------------

   void clear() {
      front = back = null;
      cursor = null;
      length = 0;
      index = -1;
   }

   void moveFront() {
      if( this.length > 0 ){
         cursor = front;
         index = 0;
      }
   }

   void moveBack() {
      if( this.length > 0 ){
         cursor = back;
         index = length - 1;
      }
   }

   void movePrev() {
      if( this.index == 0 ){
         cursor = null;
         index = -1;
      } else if( this.index > 0 ){
         cursor = cursor.prev;
         index--;
      }
   }

   void moveNext() {
      if( cursor != null ){        
         if( this.index == (this.length - 1) ){
            cursor = null;
            index = -1;
         } else if( this.index < (this.length - 1) ){
            cursor = cursor.next;
            index++;
         }
      }
   }

   void prepend(Object data) {
      Node N = new Node(data);
      if( this.length == 0 ){
         front = back = N;
      } else if( this.length > 0 ){
         N.next = front;
         front.prev = N;
         front = N;
      }
      if( cursor != null ) index++;
      length++;
   }

   void append(Object data) {
      Node N = new Node(data);
      if( this.length == 0 ){
         front = back = N;
      } else if( this.length > 0 ){
         back.next = N;
         N.prev = back;
         back = N;
      }
      length++;
   }

   void insertBefore(Object data) {
      if( this.length <= 0 ){
         throw new RuntimeException(
            "List Error: insertBefore(Object data) called on empty List");
      }
      if( index < 0 ){
         throw new RuntimeException(
            "List Error: insertBefore(int data) called with undefined cursor");
      }
      
      Node N = new Node(data);
      if( this.length == 1 || this.index == 0 ){
         N.next = front;
         front.prev = N;
         front = N;
      } else if( this.length > 0){
         N.next = cursor;
         (cursor.prev).next = N;
         N.prev = cursor.prev;
         cursor.prev = N;
      }
      index++;
      length++;
   }

   void insertAfter(Object data) {
      if( this.length == 0){
         throw new RuntimeException(
            "List Error: insertAfter(Object data) called on empty List");
      }
      if( index < 0 ){
         throw new RuntimeException(
            "List Error: insertAfter(Object data) called with undefined cursor");
      }

      Node N = new Node(data);
      if( this.length == 1 || this.index == (this.length-1) ){
         back.next = N;
         N.prev = back;
         back = N;
      } else if( this.length > 0 ){
         N.prev = cursor;
         (cursor.next.prev) = N;
         N.next = cursor.next;
         cursor.next = N;
      }
      length++;
   }

   void deleteFront() {
      if( length <= 0 ){
         throw new RuntimeException(
            "List Error: deleteFront() called on empty List");
      }
      if( this.length > 1 ){
         front = front.next;
         front.prev = null;
         index--;
      } else {
         front = back = null;
         cursor = null;
         index = -1;
      }
      length--;
   }

   void deleteBack() {
      if( this.length <= 0 ){
         throw new RuntimeException(
            "List Error: deleteBack() called on empty List");
      }
      if( this.length > 1 ){
         if( cursor == back ){
            cursor = null;
            index = -1;
         }
         back = back.prev;
         back.next = null;
      } else {
         front = back = null;
         cursor = null;
         index = -1;
      }
      length--;
   }

   void delete() {
      if( this.length <= 0 ){
         throw new RuntimeException(
            "List Error: delete() called on empty List");
      }
      if( this.index < 0 ){
         throw new RuntimeException(
            "List Error: delete() called with undefined cursor");
      }
      if( this.length > 1 ){
         if( index == 0 ){
            front = front.next;
            front.prev = null;
         }
         else if( index == (length-1) ){
            back = back.prev;
            back.next = null;
         } else {
            (cursor.prev).next = cursor.next;
            (cursor.next).prev = cursor.prev;
         }
      } else if( this.length == 1){ front = back = null; }

      cursor = null;
      index = -1;
      length--;
   }

   // Other Methods --------------------------------------------

   public String toString() {
      StringBuffer sb = new StringBuffer();
      Node N = front;
      while(N != null){
         sb.append(N.toString());
         if(N != back) sb.append(" ");
         N = N.next;
      }         
      
      return new String(sb);
   }
}
     
