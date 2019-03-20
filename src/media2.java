import javazoom.jl.player.Player;
import java.io.*;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class media2 {
    public static void main(String args[])throws Exception
    {
        InputStreamReader inr=new InputStreamReader(System.in);
        BufferedReader buf=new BufferedReader(inr);
        File f=new File("E:\\Music and Movie Trailers");
        File lof[]=f.listFiles();
        int k=1;
        for(int i=0;i<lof.length;i++)
        {
            if((lof[i].getName()).endsWith(".mp3"))
            {
                File fn=new File("E:\\Music and Movie Trailers\\"+lof[i].getName());
                InputStream in=new FileInputStream(fn);
                ContentHandler hn=new DefaultHandler();
                Metadata meta=new Metadata();
                Parser parser=new Mp3Parser();
                ParseContext parseCtx=new ParseContext();
                parser.parse(in, hn, meta, parseCtx);
                //in.close();
                System.out.println(k+".Title: "+meta.get("title"));
                System.out.println("  Artist: "+meta.get("xmpDM:artist"));
                System.out.println("  Album: "+meta.get("xmpDM:album"));
                System.out.println("  Genre: "+meta.get("xmpDM:genre"));
                k++;
                System.out.println();
            }    
        } 
        System.out.println("There are "+(k-1)+" songs in the folder.");
        System.out.println();
        System.out.println("Do you wish to select songs directly from the above list by typing their code?");
        System.out.println();
        System.out.println("Or do you wish to search  and pick from a smaller list derived from the same list as above?");
        System.out.println();
        System.out.println("For DIRECT PICKING, type the integer 1");
        System.out.println();
        System.out.println("For SEARCHING THE FILE OF YOUR CHOICE, type the integer 2");
        int ch=Integer.parseInt(buf.readLine());
        switch(ch)
        {
        case 1:    
        System.out.println("Enter codes from 1 to 69 for the songs to be played. Only 1 to 69 permitted.");
        int m=0;
        int cod[]=new int[k-1]; 
        while(true)
        {
            System.out.println("Enter code of desired song.");
            cod[m]=Integer.parseInt(buf.readLine());
            m++;
            System.out.println("Do you wish to add more songs to the list? If yes. type y");
            String c=buf.readLine();
            if(c.compareToIgnoreCase("y")!=0)
            break;    
        }
        System.out.println();
        System.out.println();
        System.out.println();
        int temp=0;
        for(int x=0;x<m-1;x++)
        {
            for(int y=x+1;y<m;y++)
            {
                if(cod[x]>cod[y])
                {
                    temp=cod[x];
                    cod[x]=cod[y];
                    cod[y]=temp;
                }    
            }
        }    
        System.out.println("Playing Playlist:");
        k=1;
        m=0;
        artwork aw=new artwork();
        while(true)
        {
            for(int i=0;i<lof.length;i++)
            {
                if((lof[i].getName()).endsWith(".mp3"))
                {
                    if(k==cod[m])
                    {
                        //FileInputStream fis=new FileInputStream("E:\\Music and Movie Trailers\\"+lof[i].getName());
                        //Player mp3=new Player(fis); 
                        System.out.println("Now Playing:");
                        File fn=new File("E:\\Music and Movie Trailers\\"+lof[i].getName());
                        InputStream in=new FileInputStream(fn);
                        ContentHandler hn=new DefaultHandler();
                        Metadata meta=new Metadata();
                        Parser parser=new Mp3Parser();
                        ParseContext parseCtx=new ParseContext();
                        parser.parse(in, hn, meta, parseCtx);
                        //in.close();
                        System.out.println(meta.get("title"));
                        System.out.println(meta.get("xmpDM:artist"));
                        System.out.println(meta.get("xmpDM:album"));
                        System.out.println(meta.get("xmpDM:genre"));
                        aw.main("E:\\Music and Movie Trailers\\"+lof[i].getName());
                        //mp3.play();
                        
                        System.out.println();
                        m++;
                      }
                    k++;
                  }    
               }    
             System.out.println("Play Again? If yes, then type y.");
             String c=buf.readLine();
             if(c.compareToIgnoreCase("y")!=0)
             break;
             else
             {
                 k=1;
                 m=0;
             }    
          }
        break;    
        case 2:
        System.out.println("Advanced Search");
        break;
        default:
        System.out.println("INCORRECT CHOICE!");    
      }
}
}    
    
    
