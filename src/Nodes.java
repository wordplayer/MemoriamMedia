import java.io.*;
public class Nodes {
    String data1;
    String data2;
    Nodes next;
    public Nodes(String data1,String data2)throws Exception
    {
        this.data1=data1;
        this.data2=data2;
    } 
    public String getData1()throws Exception
    {
        return data1;
    } 
    public String getData2()throws Exception
    {
        return data2;
    }
    public Nodes getNext()throws Exception
    {
        return next;
    }
    public void setNext(Nodes next)throws Exception
    {
        this.next=next;
    } 
    public Nodes(String data1,String data2,Nodes next)throws Exception
    {
        this.data1=data1;
        this.data2=data2;
        this.next=next;
    }        
}
