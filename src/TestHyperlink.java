
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class TestHyperlink extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        Scene scene=new Scene(new Group());
        stage.setTitle("Hyperlink Sample");
        stage.setWidth(400);
        stage.setHeight(400);
        String source="E:\\Music and Movie Trailers\\27 Michael Buble - Hollywood.mp3";
        final Media media=new Media(new File(source).toURI().toString());
        final MediaPlayer player=new MediaPlayer(media);
        final ImageView iv=new ImageView();
       // MediaPlayer player=new MediaPlayer(media);
        Mp3File mp3=new Mp3File(source);
        final ID3v2 id3v2Tag=mp3.getId3v2Tag();
        final Hyperlink hp=new Hyperlink(id3v2Tag.getTitle());
        hp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
             // iv.setImage((Image) media.getMetadata());
                 player.play();
            }
        });
        Button button=new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                hp.setVisited(false);
                iv.setImage(null);
            }
        });
        VBox vbox=new VBox();
        vbox.getChildren().addAll(hp);
        vbox.getChildren().add(button);
        vbox.setSpacing(5);
        ((Group)scene.getRoot()).getChildren().addAll(vbox,iv);
        
        vbox.setStyle("-fx-background-color:#ff1a201e");
        stage.setScene(scene);
        stage.show();
    }
    
}
