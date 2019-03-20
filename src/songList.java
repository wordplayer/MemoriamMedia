import com.mpatric.mp3agic.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
public class songList {
    static Node start;
    static int count;
    public songList(Node start)throws Exception
    {
        this.start=start;
    }
    public void insert(String song)throws Exception
    {
        if(this.start==null)
        this.start=new Node(song,this.start);
        else
        {
            Node temp=this.start;
            while(temp.getNext()!=null)
            temp=temp.getNext();
            Node newNode=new Node(song,null);
            temp.setNext(newNode);
        }    
    }
    public void display()throws Exception
    {
       // Node temp=this.start;
      //  char ch='T';
       System.out.println("SONGS:");
       JFrame frame=new JFrame("MemoriamMedia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(1000,600));
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(this.count,1));
        panel.setMaximumSize(new Dimension(1000,400));
        JButton button;
      // for(char ch='A';ch<='Z';ch++)
      // {    
         Node temp=this.start;
         while(temp!=null)
        {
           // String file=temp.getData();
           // if(file.charAt(0)==ch)
           // System.out.println(temp.getData());
            button=new JButton(temp.getData());
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setPreferredSize(new Dimension(800,30));
            panel.add(button);
            temp=temp.getNext();
        }
        JScrollPane jsp=new JScrollPane(panel,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(jsp);
        frame.pack();
        frame.setVisible(true);
     // } 
    } 
    public static void mainList(char ch)throws Exception
    {
        File f=new File(ch+":\\");
        File lof[]=f.listFiles();
        //songList obj=new songList(start);
        try
        {
            for(int i=0;i<lof.length;i++)
            {
                if((lof[i].getName()).endsWith(".mp3"))
                {
                    Mp3File mp3=new Mp3File(ch+":\\"+lof[i].getName());
                    if(mp3.hasId3v2Tag())
                    {
                        ID3v2 id3v2Tag=mp3.getId3v2Tag();
                        new songList(start).insert(id3v2Tag.getTitle());
                        count++;
                    } 
                } 
                else if((new File(ch+":\\"+lof[i].getName())).isDirectory())
                new songList(start).getFilename(ch+":\\"+lof[i].getName());    
            }
            //new songList(start).display();
            System.out.println(count);
            
        }
        catch(Exception e)
        {
            ;
        }    
    }
    
    public void getFilename(String file)throws Exception
    {
        File f=new File(file);
        File lof[]=f.listFiles();
        try
        {
            for(int i=0;i<lof.length;i++)
            {
                if(lof[i].getName().endsWith(".mp3"))
                {
                    Mp3File mp3=new Mp3File(file+"\\"+lof[i].getName());
                    if(mp3.hasId3v2Tag())
                    {
                        ID3v2 id3v2Tag=mp3.getId3v2Tag();
                        new songList(start).insert(id3v2Tag.getTitle());
                        this.count++;
                    }
                } 
                else if(new File(file+"\\"+lof[i].getName()).isDirectory())
                getFilename(file+"\\"+lof[i].getName());    
            }    
        }
        catch(Exception e)
        {
            ;
        }    
    }
     public static void main(String args[])throws Exception
    {
        //artistList obj=new artistList(null);
        //songList obj1=new songList();
        char ch='C';
        int count=0;
        //System.out.println("ARTISTS:");
        System.out.println("SONGS:");
        while(true)
        {
            if(new File(ch+":\\").isDirectory())
           // obj.main(ch);
            mainList(ch);    
            else
            count++;
            ch++;
            if(count>2)
            break;    
       }
       new songList(start).display();
   }       
}
