import java.io.*;
import java.util.*;
public class Node {
     String data;
     Node next;
     public Node(String data)throws Exception
     {
         this.data=data;
     }
     public String getData()throws Exception
     {
         return data;
     }
     public Node getNext()throws Exception
     {
         return next;
     }
     public void setNext(Node next)throws Exception
     {
         this.next=next;
     }
     public Node(String data,Node next)throws Exception
     {
         this.data=data;
         this.next=next;
     }        
}
