import java.io.*;
import javax.swing.JOptionPane;
import javazoom.jl.player.Player;
//import com.mpatric.mp3agic.*;
public class playPause {
    Player player;
    FileInputStream fis;
    BufferedInputStream bis;
    boolean canResume;
    String path;
    static int total;
    static int stopped;
    boolean valid;
    int playerStatus;
    final int NOTSTARTED=0;
    final int PLAYING=1;
    final int PAUSED=2;
    final int FINISHED=3;
    
    public playPause()
    {
        player=null;
        fis=null;
        canResume=false;
        path=null;
        total=0;
        stopped=0;
        valid=false;
        playerStatus=NOTSTARTED;
    }
    public boolean canResume()
    {
        return canResume;
    }
    public void setPath(String path)
    {
        this.path=path;
    }
    public void pause()throws Exception
    {
        playerStatus=PAUSED;
        stopped=fis.available();
        //System.out.println(stopped);
        player.close();
        fis=null;
        bis=null;
        player=null;
        if(valid)
        canResume=true;    
    }
    public void resume()throws Exception
    {
        if(!canResume)
        return;
        if(play(total-stopped))
        {    
        canResume=false;
        //System.out.println(total-stopped);
        }
    }
    public boolean play(int pos)throws Exception
    {
        valid=true;
        canResume=false;
        try
        {
            fis=new FileInputStream(path);
            total=fis.available();
            //System.out.println(total);
            if(pos>-1)
            fis.skip(pos);
            bis=new BufferedInputStream(fis);
            player=new Player(bis);
            new Thread(
                    new Runnable()
                    {
                        public void run()
                        {
                            try
                            {
                                player.play();
                                playerStatus=PLAYING;
                            }
                            catch(Exception e)
                            {
                                JOptionPane.showMessageDialog(null,"Error playing mp3 file");
                                valid=false;
                            }    
                        }       
                    }
                    ).start();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error playing mp3 file");
            valid=false;
        }
        return valid;
    }
    public static void main(String args[])throws Exception
    {
        InputStreamReader inr=new InputStreamReader(System.in);
        BufferedReader buf=new BufferedReader(inr);
        playPause mp3=new playPause();
        //int k=0;
        //Mp3File mp3file=new Mp3File(file);
        mp3.setPath("C:\\Users\\Smart User\\Desktop\\Ready Or Not.mp3");
        mp3.play(-1);
        System.out.println("Press ENTER to pause play.To resume play,again press ENTER");
        while(true)
        {    
        char c=(char)buf.read();
        if(c=='\n')
        mp3.pause();
        char v=(char)buf.read();
        if(v=='\n')
        mp3.resume();
        System.out.println(total-stopped);
        if((total-stopped)==total-1)
        {   
            System.out.println("File played.");
            break;
        }    
       }
    }        
}
