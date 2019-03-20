
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class convertingImages extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Artwork");
        Group root=new Group();
        String source="C:\\Users\\Smart User\\Downloads\\Blackbird\\10 Watch Over You.mp3";
        //Media media=new Media(new File(source).toURI().toString());
        Mp3File mp3=new Mp3File(source);
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        byte[] imageData=id3v2Tag.getAlbumImage();
        BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
        Image image=SwingFXUtils.toFXImage(img, null);
        ImageView iv=new ImageView();
        iv.setImage(image);
        iv.setFitWidth(1200);
        iv.setFitHeight(500);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        root.getChildren().add(iv);
        Scene scene=new Scene(root,500,500);
        stage.setScene(scene);
        stage.show();
    }
    
}
