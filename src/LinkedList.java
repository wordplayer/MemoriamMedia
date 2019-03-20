import java.io.*;
import java.util.*;
public class LinkedList {
    Node1 start=new Node1();
    public void insert(int data)throws Exception
    {
        if(this.start==null)
        {    
            System.out.println("numbers.");
            this.start.data=data;
            //this.start.next=null;
        }
        else
        {
            Node1 temp=start;
            while(temp.next!=null)
            temp=temp.next;
            temp.data=data;
            temp.next=null;
        }    
    }
    public void display()throws Exception
    {
        if(this.start==null)
        System.out.println("No elements in the list.");
        else
        {
            Node1 temp=this.start;
            System.out.println("List:");
            while(temp!=null)
            {    
                System.out.println(temp.data);
                temp=temp.next;
            }
        }    
    }
    public static void main(String args[])throws Exception
    {
        InputStreamReader inr=new InputStreamReader(System.in);
        BufferedReader buf=new BufferedReader(inr);
        LinkedList obj=new LinkedList();
        do
        {
            System.out.println("Enter integer for the list.");
            int n=Integer.parseInt(buf.readLine());
            obj.insert(n);
            System.out.println("Continue entering? Enter y to continue.");
            String ch=buf.readLine();
            if(ch.compareToIgnoreCase("y")!=0)
            break;    
        } 
        while(true);
        obj.display();
    }        
}
