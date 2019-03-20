import javafx.scene.media.MediaPlayer;
import com.mpatric.mp3agic.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;


public class TestPlayer extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("MemoriamMedia");
        stage.setWidth(850);
        stage.setHeight(650);
        Group root=new Group();
        String source="E:\\OneRepublic -Native\\01-OneRepublic -Counting Stars.mp3.mp3";
        Mp3File mp3=new Mp3File(source);
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        Media media=new Media(new File(source).toURI().toString());
        MediaPlayer player=new MediaPlayer(media);
        StackPane layout=new StackPane();
        layout.setPrefWidth(850);
        layout.setPrefHeight(613);
        layout.setStyle("-fx-background-color: black;");
        String pl="Now Playing:";
        Label lab1=new Label(pl);
        lab1.setWrapText(true);
        String songdes=(id3v2Tag.getArtist()).toUpperCase()+"\n"+id3v2Tag.getTitle()+"\n"+id3v2Tag.getAlbum();
        Label lab2=new Label(songdes);
        lab2.setWrapText(true);
        lab1.setAlignment(Pos.TOP_LEFT);
        lab2.setAlignment(Pos.BOTTOM_LEFT);
        lab1.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size:14; -fx-text-fill: white;");
        lab2.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size:14; -fx-text-fill: white;");
        final FadeTransition fin1=new FadeTransition(Duration.millis(100),lab1);
        fin1.setFromValue(0.0);
        fin1.setToValue(1.0);
        final FadeTransition fout1=new FadeTransition(Duration.millis(100),lab1);
        fout1.setFromValue(1.0);
        fout1.setToValue(0.0);
        final FadeTransition fin2=new FadeTransition(Duration.millis(100),lab2);
        fin2.setFromValue(0.0);
        fin2.setToValue(1.0);
        final FadeTransition fout2=new FadeTransition(Duration.millis(100),lab2);
        fout2.setFromValue(1.0);
        fout2.setToValue(0.0);
        player.setAutoPlay(true);
        root.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
               fin1.play();
               fin2.play();
            }
        });
        root.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fout1.play();
                fout2.play();
            }
        });
        byte imageData[]=id3v2Tag.getAlbumImage();
        try
        {
            BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
            Image image=SwingFXUtils.toFXImage(img, null);
            ImageView iv=new ImageView();
            iv.setImage(image);
            iv.setFitHeight(600);
            iv.setFitWidth(800);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setOpacity(0.3);
            
            layout.getChildren().setAll(iv,lab1,lab2);
            layout.setAlignment(lab1,Pos.TOP_LEFT);
            layout.setAlignment(iv,Pos.CENTER);
            layout.setAlignment(lab2,Pos.BOTTOM_LEFT);
            //layout.setStyle("-fx-background-color: black;");
            root.getChildren().add(layout);
        }
        catch(Exception e)
        {
            Image image=new Image(new File("C:\\Users\\Smart User\\Desktop\\default artwork.jpg").toURI().toString());
            ImageView iv=new ImageView();
            iv.setImage(image);
            iv.setFitWidth(700);
            iv.setFitHeight(500);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setOpacity(0.3);
            /*lab1.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 14; -fx-text-fill: white;");
            lab2.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 14; -fx-text-fill: white;");*/
            layout.getChildren().setAll(iv,lab1,lab2);
            layout.setAlignment(lab1,Pos.TOP_LEFT);
            layout.setAlignment(iv,Pos.CENTER);
            layout.setAlignment(lab2,Pos.BOTTOM_LEFT);
            
            root.getChildren().add(layout);
        }    
        player.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                stage.close();
            }
        });
        
        Scene scene=new Scene(root,850,650);
        stage.setScene(scene);
        stage.show();
    }
    
}