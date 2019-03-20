import com.mpatric.mp3agic.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class TestingImage {
    public static void main(String args[])throws Exception
    {
        Mp3File mp3=new Mp3File("C:\\Users\\Smart User\\Downloads\\05-ed_sheeran_-_lego_house.mp3");
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        try
        {
            byte image[]=id3v2Tag.getAlbumImage();
            InputStream in=new ByteArrayInputStream(image);
            BufferedImage im=ImageIO.read(in);
            ColorModel model=im.getColorModel();
            String det=model.toString();
            System.out.println(det);
            System.out.println(model.hashCode());
            System.out.println(model.getNumColorComponents());
        }
        catch(Exception e)
        {
            System.out.println("No image.");
        }    
    }        
}
