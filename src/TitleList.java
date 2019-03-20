
import java.io.File;
import com.mpatric.mp3agic.*;

 
public class TitleList {
    static Node start;
    static int count;
    public TitleList(Node start)throws Exception
    {
        this.start=start;
    }
    public void insert(String data)throws Exception
    {
        if(this.start==null)
        this.start=new Node(data,this.start);
        else
        {
            Node temp=this.start;
            while(temp.getNext()!=null)
            {
                temp=temp.getNext();
            }
            Node newNode=new Node(data,null);
            temp.setNext(newNode);
        }    
    }
    public void display()throws Exception
    {
        Node temp=this.start;
        System.out.println("SONGS:");
        while(temp!=null)
        {
            System.out.println(temp.getData());
            temp=temp.getNext();
        }    
    }
    public void getFilename(String file)throws Exception
    {
        File f=new File(file);
        File lof[]=f.listFiles();
        for(int i=0;i<lof.length;i++)
        {
            if(lof[i].getName().endsWith(".mp3"))
            {
                Mp3File mp3=new Mp3File(file+"\\"+lof[i].getName());
                if(mp3.hasId3v2Tag())
                {
                    ID3v2 id3v2Tag=mp3.getId3v2Tag();
                    new TitleList(this.start).insert(id3v2Tag.getTitle());
                    this.count++;
                }    
            }
            else if(new File(file+"\\"+lof[i].getName()).isDirectory())
            {
                getFilename(file+"\\"+lof[i].getName());
            }    
        }    
    }
    public static void main(String args[])throws Exception
    {
        File f=new File("E:\\");
        File lof[]=f.listFiles();
        for(int i=0;i<lof.length;i++)
        {
            if(lof[i].getName().endsWith(".mp3"))
            {
                Mp3File mp3=new Mp3File("E:\\"+lof[i].getName());
                if(mp3.hasId3v2Tag())
                {
                    ID3v2 id3v2Tag=mp3.getId3v2Tag();
                    new TitleList(start).insert(id3v2Tag.getTitle());
                    count++;
                }    
            }
            else if(new File("E:\\"+lof[i].getName()).isDirectory())
            new TitleList(start).getFilename("E:\\"+lof[i].getName());
        }
        new TitleList(start).display();
        System.out.println(count);
    }        
}
