import javazoom.jl.player.Player;
import java.io.*;
public class playingMedia1 {
    public static void main(String args[])throws Exception
    {
        InputStreamReader in=new InputStreamReader(System.in);
        BufferedReader buf=new BufferedReader(in);
        File f=new File("E:\\Music and Movie Trailers");
        File lof[]=f.listFiles();
        int k=1;
        System.out.println("The audio files in the folder are:");
        for(int i=0;i<lof.length;i++)
        {
            if((lof[i].getName()).endsWith(".mp3"))
            {    
            System.out.println(k+"."+lof[i].getName());
            k++;
            }
        }
        System.out.println();
       /* System.out.println("Please enter the integer code of the audio file to be played.");
        int code=Integer.parseInt(buf.readLine());
        k=1;
        for(int i=0;i<lof.length;i++)
        {
            if((lof[i].getName()).endsWith(".mp3"))
            {    
              if(code==k)
               {
                FileInputStream fis=new FileInputStream("E:\\Music and Movie Trailers\\"+lof[i].getName());
                Player mp3=new Player(fis);
                mp3.play();
                break;
              }
              k++;
           }
        }*/      
}
}    
