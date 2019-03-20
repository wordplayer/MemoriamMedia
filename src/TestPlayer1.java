
import java.io.File;
import com.mpatric.mp3agic.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;


public class TestPlayer1 extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setFullScreen(true);
        Group root=new Group();
        StackPane layout=new StackPane();
        HBox hb=new HBox();
        //hb.setPrefWidth(2000);
        hb.setPrefHeight(8.0);
        hb.setStyle("-fx-background-color: grey");
        
        String header="MemoriamMedia";
        Label label1=new Label(header);
        label1.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 14; -fx-text-fill: black;");
        hb.getChildren().add(label1);
        hb.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(hb, Priority.ALWAYS);
        root.getChildren().add(hb);
        String source="E:\\Monuments\\08. Regenerate.mp3";
        Media media=new Media(new File(source).toURI().toString());
        MediaPlayer mp=new MediaPlayer(media);
        Mp3File mp3=new Mp3File(source);
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        String songdes=id3v2Tag.getArtist().toUpperCase()+"\n"+id3v2Tag.getTitle()+"\n"+id3v2Tag.getAlbum();
        Label lb=new Label(songdes);
        lb.setStyle("-fx-font-family: \"Helvetica\";-fx-font-size: 26;-fx-text-fill:white;");
        lb.setAlignment(Pos.TOP_RIGHT);
        root.getChildren().add(lb);
        byte imageData[]=id3v2Tag.getAlbumImage();
        try
        {
            BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
            Image image=SwingFXUtils.toFXImage(img, null);
            ImageView iv=new ImageView();
            iv.setImage(image);
            iv.setFitWidth(1200);
            iv.setFitHeight(800);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setOpacity(0.4);
            layout.getChildren().add(iv);
            //layout.setAlignment(Pos.CENTER);
            layout.setAlignment(iv,Pos.CENTER);
            //layout.setAlignment(lb,Pos.CENTER_RIGHT);
            root.getChildren().add(layout); 
        }
        catch(Exception e)
        {
            Image image=new Image(new File("C:\\Users\\Smart User\\Desktop\\default artwork.jpg").toURI().toString());
            ImageView iv=new ImageView();
            iv.setImage(image);
            iv.setFitWidth(1200);
            iv.setFitHeight(800);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setOpacity(0.4);
            layout.getChildren().add(iv);
            layout.setAlignment(iv,Pos.CENTER);
            //layout.setAlignment(lb,Pos.CENTER_RIGHT);
            root.getChildren().add(layout);
            
        }    
        Scene scene=new Scene(root);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
    
}
