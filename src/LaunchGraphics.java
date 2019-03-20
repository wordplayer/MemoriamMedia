import com.mpatric.mp3agic.*;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class LaunchGraphics extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MemoriamMedia");
        Group root=new Group();
        String source="E:\\Downloads\\a rocket to the moon-07 like we used to.mp3";
        Media media=new Media(new File(source).toURI().toString());
        final MediaPlayer player=new MediaPlayer(media);
        stage.setWidth(600);
        stage.setHeight(500);
        Mp3File mp3=new Mp3File(source);
        ID3v2 id3v2Tag=mp3.getId3v2Tag();
        long length=mp3.getLengthInSeconds();
        System.out.println(length*1000);
        System.out.println((length/60)+":"+(length%60));
        player.setOnReady(new Runnable() {

            @Override
            public void run() {
                System.out.println(player.getMedia().getDuration());
            }
        });
        Duration d=new Duration(mp3.getLengthInMilliseconds());
        BuildGraphics obj=new BuildGraphics(player,id3v2Tag,stage,d);
        Scene scene=new Scene(root,500,407.5);
        scene.setRoot(obj);
        stage.setScene(scene);
        stage.show();
    }
    
}
