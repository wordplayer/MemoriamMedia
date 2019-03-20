import java.awt.BorderLayout;
import java.awt.Component;
import javafx.scene.media.Media;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
public class MediaPlayer extends javax.swing.JPanel{
    public MediaPlayer(String file)throws Exception
    {
        //initComponents();
        setLayout(new BorderLayout());
        try
        {
            Player mediaPlayer=Manager.createRealizedPlayer(new MediaLocator(file));
            Component video=mediaPlayer.getVisualComponent();
            Component control=mediaPlayer.getControlPanelComponent();
            if(video!=null)
            {
                add(video,BorderLayout.CENTER);
            } 
            add(control,BorderLayout.SOUTH);
            mediaPlayer.start();
        } 
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    } 
    public static void main(String args[])throws Exception
    {
        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        String file="C:\\Users\\Smart User\\Downloads\\Aaj Jane Ki Zid Na Karo-shafqat.avi";
        JFrame mediaTest=new JFrame("Movie Player");
        mediaTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MediaPlayer mediaPanel=new MediaPlayer(file);
        mediaTest.add(mediaPanel);
        mediaTest.setSize(800,700);
        mediaTest.setLocationRelativeTo(null);
        mediaTest.setVisible(true);
    }        

    MediaPlayer(Media media) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
