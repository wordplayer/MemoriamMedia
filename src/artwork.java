import java.io.*;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.image.*;
import java.awt.Graphics2D;
import javax.imageio.*;
import com.mpatric.mp3agic.*;
import javazoom.jl.player.Player;
public class artwork {
    public static void main(String file)throws Exception
    {
        Mp3File mp3file=new Mp3File(file);
        if(mp3file.hasId3v2Tag())
        {
            ID3v2 id3v2Tag=mp3file.getId3v2Tag();
            byte imageData[]=id3v2Tag.getAlbumImage();
            if(imageData!=null)
            {
                String mimeType=id3v2Tag.getAlbumImageMimeType();
                //System.out.println("debug:: imageData is not null");
                BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
                JFrame frame=new JFrame();
                ImageIcon icon=new ImageIcon(img);
                JLabel label=new JLabel(icon);
                frame.add(label);
                frame.setVisible(true);
                frame.pack();
                Graphics g=img.getGraphics();
                System.out.println("Image size: "+img.getWidth()+"x"+img.getHeight());
                g.setFont(g.getFont().deriveFont(1,12f));
                g.setColor(Color.red);
                g.drawString("Now Playing:", 10, 10);
                g.drawString((id3v2Tag.getArtist()).toUpperCase(), 10, img.getHeight()-80);
                g.drawString(id3v2Tag.getTitle(), 10, img.getHeight()-66);
                g.drawString(id3v2Tag.getAlbum(), 10, img.getHeight()-52);
                playingMedia3 mp3=new playingMedia3(new FileInputStream(file));
                mp3.main(file);
                
                
                /*FileInputStream fis=new FileInputStream(file);
                Player mp3=new Player(fis);
                mp3.play();*/
                frame.dispose();
              }
            else
            {    
            System.out.println("NO ALBUM ARTWORK");
            playingMedia3 mp3=new playingMedia3(new FileInputStream(file));
            mp3.main(file);
            }
         }
        
    }
    
}
