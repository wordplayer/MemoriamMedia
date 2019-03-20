import com.sun.jna.Native;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import com.sun.jna.NativeLibrary;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class videoPlayer {
   private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
   public static void main(String args[])throws Exception
   {
       SwingUtilities.invokeLater(new Runnable() 
       {
           public void run()
           {
               try
               {    
               new videoPlayer();
               }
               catch(Exception e)
               {
                   e.printStackTrace();
               }    
           }        
       });        
   }
    public videoPlayer()throws Exception
    {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
        Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(),LibVlc.class);
        JFrame frame=new JFrame("Video Player");
        mediaPlayerComponent=new EmbeddedMediaPlayerComponent();
        frame.setContentPane(mediaPlayerComponent);
        frame.setLocation(100,100);
        frame.setSize(1050,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        System.out.println("Running.....");
        mediaPlayerComponent.getMediaPlayer().playMedia("C:\\Users\\Smart User\\Downloads\\Blue - One Love.mkv");
    }        
}
