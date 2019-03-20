import java.io.*;
import java.util.*;
import com.mpatric.mp3agic.*;
public class artistList {
    static Node start;
    public artistList(Node start)throws Exception
    {
        this.start=start;
    }
    public void insert(String artist)throws Exception
    {
        //System.out.println(artist);
        if(this.start==null)
        this.start=new Node(artist,this.start);
        else
        {
            Node temp=this.start;
            //System.out.println("ASBNSDH");
            while(temp.getNext()!=null)
            {    
                //System.out.println();
                //System.out.println(artist);
                temp=temp.getNext();
            }
            Node newNode=new Node(artist,null);
            temp.setNext(newNode);
            //temp=new Node(artist,null);
        }    
    }
    public void access()throws Exception
    {
        //System.out.println();
        //alphaList obj=new alphaList();
        //System.out.println("getting artists.");
        //char c='C';
       // while(c<='Z')
       // {    
         Node temp=this.start;
        //System.out.println(temp==null);
        //System.out.println(this.start.getData());
        // System.out.println(c);
        while(temp!=null)
        {
            //System.out.println("artists.");
            String file=temp.getData();
            String file1=file.toUpperCase();
            //System.out.println(file);
            //System.out.println();
            //if(file1.charAt(0)==c)
            //String file=temp.getData();
            System.out.println(file);
            //System.out.println((temp.getData()).charAt(0));
            //obj.main(temp.getData());
            temp=temp.getNext();
       //  }
        // System.out.println(c);
      }         
    }
     
   public static void main(char ch)throws Exception
    {
        //char ch='C';
        //listingFiles obj=new listingFiles();
        //artistList al=new artistList();
        //while(new File(ch+":\\").isDirectory())
        //{    
        File f=new File(ch+":\\");
        File lof[]=f.listFiles();
        //if(lof!=null)
        //{  
        //System.out.println("ARTISTS:");
        try
        {    
        for(int i=0;i<lof.length;i++)
        {
            if((lof[i].getName()!=null))
            {    
            if((lof[i]).getName().endsWith(".mp3"))
            {    
            //System.out.println(lof[i].getName());
             Mp3File mp3=new Mp3File(ch+":\\"+lof[i].getName());
             if(mp3.hasId3v2Tag())
             {    
             ID3v2 id3v2Tag=mp3.getId3v2Tag();
             //System.out.println();
             new artistList(start).insert(id3v2Tag.getArtist());
             //System.out.println("Artist: "+id3v2Tag.getArtist());
             /*System.out.println("Title: "+id3v2Tag.getTitle());
             System.out.println("Album: "+id3v2Tag.getAlbum());
             System.out.println("Artist: "+id3v2Tag.getArtist());
             System.out.println("Year: "+id3v2Tag.getYear());*/
             }
            }
            else if((new File(ch+":\\"+(lof[i].getName())).isDirectory()) && (lof[i].getName()!=null))
            new artistList(start).printFilename(ch+":\\"+(lof[i].getName())) ;
            //al.display();
            }
            }
           new artistList(start).access();
        }
            catch(Exception e)
            {
                ;
                //System.out.println();
            }    
        
        //} 
        //ch++;
     //}
   }
    public void printFilename(String file)throws Exception
    {
        File f=new File(file);
        File lof[]=f.listFiles();
        //artistList al=new artistList(start);
        //listingFiles obj=new listingFiles();
        try
        {    
        for(int i=0;i<lof.length;i++)
        {
           if(lof[i].getName()!=null)
            {    
            if((lof[i].getName()).endsWith(".mp3"))
            {
               Mp3File mp3=new Mp3File(file+"\\"+lof[i].getName());
               if(mp3.hasId3v2Tag())
               {
                  ID3v2 id3v2Tag=mp3.getId3v2Tag();
                  new artistList(start).insert(id3v2Tag.getArtist());
                  //System.out.println("Artist: "+id3v2Tag.getArtist());
                  /*System.out.println();
                  System.out.println("Title: "+id3v2Tag.getTitle());
                  System.out.println("Album: "+id3v2Tag.getAlbum());
                  System.out.println("Artist: "+id3v2Tag.getArtist());
                  System.out.println("Year: "+id3v2Tag.getYear());*/  
              }    
            }
            else if((new File(file+"\\"+lof[i].getName()).isDirectory()))
            printFilename(file+"\\"+lof[i].getName());
            
            }
            }
        }
            catch(Exception e)
            {
                ;
                //System.out.println();
            }    
        }
} 