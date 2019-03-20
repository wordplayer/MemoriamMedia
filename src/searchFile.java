import java.io.*;
import javazoom.jl.player.Player;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class searchFile {
    public static void main(String args[])throws IOException
    {
        File fn=new File("E:\\Music and Movie Trailers");
        File lof[]=fn.listFiles();
        int k=1;
        System.out.println("Just a casual question. Might help in choosing the type of search for you.");
        System.out.println("");
        for(int i=0;i<lof.length;i++)
        {
            System.out.println(lof[i].getName());
        }    
    }        
}
