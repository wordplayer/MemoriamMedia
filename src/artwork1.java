
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class artwork1 {
    public static void main(String args[])throws Exception
    {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String source="C:\\Users\\Smart User\\Downloads\\Blackbird\\10 Watch Over You.mp3";
        Mp3File mp3=new Mp3File(source);
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        byte imageData[]=id3v2Tag.getAlbumImage();
        BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
        ImageIcon icon=new ImageIcon(img);
        JLabel label=new JLabel(icon);
        frame.add(label);
        frame.setVisible(true);
        frame.pack();
    }        
}
