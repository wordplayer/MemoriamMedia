
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import com.mpatric.mp3agic.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javax.imageio.ImageIO;

public class TestGraphics extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setFullScreen(true);
        Group root=new Group();
        String source="C:\\Users\\Smart User\\Downloads\\06 So Far Away.mp3";
        Mp3File mp3=new Mp3File(source);
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        Media media=new Media(new File(source).toURI().toString());
        MediaPlayer player=new MediaPlayer(media);
        player.setAutoPlay(true);
        TestWindow1 obj=new TestWindow1(id3v2Tag,player);
        Scene scene=new Scene(root);
        scene.setRoot(obj);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    
}
