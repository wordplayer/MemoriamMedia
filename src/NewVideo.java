
import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


public class NewVideo extends Application {

    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
       stage.setTitle("MemoriamMedia");
       Group root=new Group();
       //Scene scene=new Scene(root,540,241);
       String source="E:\\Videos 4\\A Rocket To The Moon_ Ever Enough [OFFICIAL VIDEO].mp4";
       Media media=new Media(new File(source).toURI().toString());
       //Media media=new Media("https://www.youtube.com/watch?v=zzTZeeMCUBk");
       MediaPlayer mediaPlayer=new MediaPlayer(media);
       mediaPlayer.setAutoPlay(true);
       MediaControl mediaControl=new MediaControl(mediaPlayer,stage);
       Scene scene=new Scene(root,1250,700);
       System.out.println(media.getWidth()+"x"+media.getHeight());
       scene.setRoot(mediaControl);
      /* MediaView mediaView=new MediaView(mediaPlayer);
       ((Group)scene.getRoot()).getChildren().add(mediaView);*/
       stage.setScene(scene);
       stage.show();
    }
 }
