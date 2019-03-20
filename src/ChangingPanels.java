import javax.swing.*;
import java.io.*;
import com.mpatric.mp3agic.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
public class ChangingPanels {
   public static void main(String args[])throws Exception
   {
       Mp3File mp3=new Mp3File("C:\\Users\\Smart User\\Desktop\\Ready Or Not.mp3");
       final ID3v2 id3v2Tag=mp3.getId3v2Tag();
       final JFrame frame=new JFrame("MemoriamMedia");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setMaximumSize(new Dimension(400,300));
       final JPanel panel=new JPanel();
       panel.setLayout(new GridLayout(1,1));
       panel.setMaximumSize(new Dimension(400,300));
       JButton button;
       button=new JButton(id3v2Tag.getTitle());
       button.setHorizontalAlignment(SwingConstants.LEFT);
       button.setPreferredSize(new Dimension(300,30));
       ActionListener al=new ActionListener() {

           @Override
           public void actionPerformed(ActionEvent e) {
               frame.getContentPane().removeAll();
               byte imageData[]=id3v2Tag.getAlbumImage();
               try {
                   BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
                   ImageIcon icon=new ImageIcon(img);
                   JLabel label=new JLabel(icon);
                   panel.add(label);
                   frame.getContentPane().add(panel);
                   frame.pack();
                   frame.setVisible(true);
               } catch (IOException ex) {
                   Logger.getLogger(ChangingPanels.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       };
       button.addActionListener(al);
       panel.add(button);
       frame.getContentPane().add(panel);
       frame.pack();
       frame.setVisible(true);
   }        
}
