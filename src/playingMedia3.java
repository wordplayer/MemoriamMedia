import javazoom.jl.player.Player;
import javazoom.jl.player.AudioDevice;
import java.io.*;
public class playingMedia3 {
    final static int NOTSTARTED=0;
    final static int PLAYING=1;
    final static int PAUSED=2;
    final static int FINISHED=3;
    final Player player;
    final Object playerLock=new Object();
    static int playerStatus=NOTSTARTED;
    public playingMedia3(final FileInputStream in)throws Exception
    {
        this.player=new Player(in);
    }
    public playingMedia3(final FileInputStream in, final AudioDevice ad)throws Exception
    {
        this.player=new Player(in,ad);
    }
    public void play()throws Exception
    {
        synchronized(playerLock)
        {
            switch(playerStatus)
            {
                case NOTSTARTED:
                final Runnable r=new Runnable()
                {
                    public void run()
                    {
                        try
                        {    
                        playInternal();
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                        }    
                    }        
                };
                    final Thread t=new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus=PLAYING;
                    t.start();
                    break;
                case PAUSED:
                resume();
                break;
                default:
                break;    
            }      
           }    
        }
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean pause()throws Exception
    {
        synchronized(playerLock)
        {
            if(playerStatus==PLAYING)
            {
                this.playerStatus=PAUSED;
            }
            return playerStatus==PAUSED;
        }    
    }
    public boolean resume()throws Exception
    {
        synchronized(playerLock)
        {
            if(playerStatus==PAUSED)
            {
                this.playerStatus=PLAYING;
                playerLock.notifyAll();
            }
           return playerStatus==PLAYING; 
        }    
    }
    public void stop()throws Exception
    {
        synchronized(playerLock)
        {
            this.playerStatus=FINISHED;
            player.notifyAll();
        }    
    }
    public void playInternal()throws Exception
    {
        while(this.playerStatus!=FINISHED)
        {
            try
            {
                if(!player.play(1))
                break;    
            }
            catch(final Exception e)
            {
                break;
            }
            synchronized(playerLock)
            {
                while(this.playerStatus==PAUSED)
                {
                    try
                    {
                        playerLock.wait();
                        
                    }
                    catch(final Exception e)
                    {
                        break;
                    }    
                }    
            }    
        }
        close();
    }        
    public void close()throws Exception
    {
        synchronized(playerLock)
        {
            playerStatus=FINISHED;
            player.close();
        }    
    }
    public static void main(String file)throws Exception
    {
        
        InputStreamReader inr=new InputStreamReader(System.in);
        BufferedReader buf=new BufferedReader(inr);
        //FileInputStream fis=new FileInputStream(file);
        playingMedia3 mp3=new playingMedia3(new FileInputStream(file));
        
        System.out.println("To pause play,press ENTER. To resume play, again press ENTER");
        while(playerStatus!=FINISHED)
        {
        mp3.play();
        if(playerStatus==FINISHED)
        break;
        else
        {    
        char c=(char)buf.read();
        if(c=='\n')
        mp3.pause();
        char v=(char)buf.read();
        if(c=='\n')
        mp3.resume();
        //if(playerStatus==FINISHED)
        //break; 
        }
       }
       playerStatus=NOTSTARTED;
       //return;
    }        
}    
          
