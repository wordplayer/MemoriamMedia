
import com.mpatric.mp3agic.ID3v2;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MediaStarter extends Application{
    Stage stage;
    MediaPlayer mp;
    ID3v2 id3v2Tag;
    Duration d;
    public MediaStarter(Stage st,MediaPlayer player,ID3v2 id3,Duration dn)throws Exception
    {
        this.stage=st;
        this.mp=player;
        this.id3v2Tag=id3;
        this.d=dn;
        start(this.stage);
    } 
   /* public void starter()
    {
        try
        {
            //stage.setTitle("MemoriamMedia");
            //stage.setWidth(1200);
            //stage.setHeight(700);
            stage.close();
            stage.setFullScreen(true);
            
        } 
        catch(Exception e)
        {
            System.out.println("Not starting properly");
        }     
    }*/        

    @Override
    public void start(Stage stage) throws Exception {
        try
        {
            stage.setFullScreen(true);
            LoadData loaddataObj = new LoadData();
            String title=id3v2Tag.getTitle();
            String lyrics = loaddataObj.dataHTML(title);
            mp.setAutoPlay(true);
            System.out.println(id3v2Tag.getLength());
            System.out.println("Lyrics: "+lyrics);
            AudioControl audioControl=new AudioControl(mp,lyrics,id3v2Tag,d);
            Scene scene=new Scene(new Group());
            scene.setRoot(audioControl);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e)
        {
            
        }    
    }
}
