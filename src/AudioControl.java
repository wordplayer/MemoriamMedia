
import com.mpatric.mp3agic.ID3v2;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import javax.imageio.ImageIO;


public class AudioControl extends BorderPane{
    private MediaPlayer mp;
    final boolean repeat=false;
    boolean stopRequested=false;
    boolean atEndOfMedia=false;
    Duration duration;
    Slider timeSlider;
    Label playTime;
    Slider volumeSlider;
    HBox mediaBar;
    StackPane layout;
    String lyrics;
    ScrollPane sc=new ScrollPane();
    ID3v2 id3v2Tag;
    ImageView iv=new ImageView();
    public AudioControl(final MediaPlayer mediaPlayer,final String lyrics,ID3v2 id3, Duration d) throws Exception
    {
        this.mp=mediaPlayer;
        this.lyrics=lyrics;
        this.id3v2Tag=id3;
        this.duration=d;
        setStyle("-fx-background-color: black;");
        //String text=artist+"\n"+title+"\n"+album;
        
        
        this.layout=new StackPane();
        this.layout.setPrefWidth(1200);
        this.layout.setPrefHeight(700);
        byte[] imageData=id3v2Tag.getAlbumImage();
        try
        {
            BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
            Image image=SwingFXUtils.toFXImage(img, null);
            iv.setImage(image);
            iv.setFitWidth(1200);
            iv.setFitHeight(500);
            iv.setOpacity(0.3);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            Label label=new Label(lyrics);
            label.setWrapText(true);
            label.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 14;-fx-text-fill: white;");
            layout.getChildren().setAll(iv,label);
            layout.setAlignment(iv,Pos.CENTER);
            layout.setStyle("-fx-background-color: black");
            VBox vbox=new VBox();
            vbox.setPrefWidth(1200);
            vbox.getChildren().add(layout);
            sc.setPrefSize(1200,500);
            sc.setContent(vbox);
            setCenter(sc);
        }
        catch(Exception e)
        {
            Image image=new Image(new File("C:\\Users\\Smart User\\Desktop\\default artwork.jpg").toURI().toString());
            ImageView iv =new ImageView();
            iv.setImage(image);
            iv.setFitWidth(1200);
            iv.setFitHeight(500);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setOpacity(0.3);
            Label label=new Label(lyrics);
            label.setWrapText(true);
            label.setStyle("-fx-font-family:\"Helvetica\";-fx-font-size: 14;-fx-text-fill: white;");
            layout.getChildren().setAll(iv,label);
            layout.setAlignment(iv,Pos.CENTER);
            layout.setStyle("-fx-background-color: black;");
            VBox vbox=new VBox();
            vbox.setPrefWidth(1200);
            vbox.getChildren().add(layout);
            sc.setPrefSize(1200,500);
            sc.setContent(vbox);
            setCenter(sc);
        }    
        
        //if(img!=null)
        //{
            
        //ImageView iv=new ImageView();
        
        //}    
        //else
        /*{
            Image image=new Image(new File("C:\\Users\\Smart User\\Desktop\\default artwork.jpg").toURI().toString());
            iv.setImage(image);
            iv.setFitWidth(1200);
            iv.setFitHeight(500);
            iv.setOpacity(0.3);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
        }*/    
        //iv.setStyle("-fx-position: fixed;");
        
        //sc.setFitToWidth(true);
        //sc.setFitToHeight(true);
        
        //sc.setFitToWidth(true);
        //sc.setFitToHeight(true);
        
       /* mp.setOnReady(new Runnable() {

            @Override
            public void run() {
                
                Image image=(Image)mp.getMedia().getMetadata().get("image");
                Label label=new Label(lyrics);
        label.setWrapText(true);
        label.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 12;-fx-text-fill: black;");
        ImageView iv=new ImageView();
        iv.setImage(image);
        iv.setFitWidth(1200);
        iv.setFitHeight(700);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setOpacity(0.3);
        iv.setCache(true);
        
        layout.getChildren().setAll(iv,label);
       // layout.getChildren().setAll(label);
        
            }
        });*/
        
        mediaBar=new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5,10,5,10));
        //HBox.setHgrow(mediaBar, Priority.ALWAYS);
        BorderPane.setAlignment(mediaBar,Pos.CENTER);
        final Button playButton=new Button();
        playButton.setStyle("-fx-background-color: white;");
        playButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                Status status=mp.getStatus();
                if(status==Status.UNKNOWN || status==Status.HALTED)
                {
                    return;
                } 
                if(status==Status.PAUSED || status==Status.READY || status==Status.STOPPED)
                {
                    if(atEndOfMedia)
                    {
                        mp.seek(mp.getStartTime());
                        atEndOfMedia=false;
                    }
                    mp.play();
                }
                else
                {
                    mp.pause();
                }    
            }
        });
        mp.currentTimeProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                try
                {
                    updateValues();
                } 
                catch(Exception e)
                {
                    System.out.println("exception with current time");
                }    
            }
        });
        mp.setOnPlaying(new Runnable() {

            @Override
            public void run() {
                if(stopRequested)
                {
                    mp.pause();
                    stopRequested=false;
                }
                else
                {
                    Image pauseImage=new Image(new File("C:\\Users\\Smart User\\Desktop\\pause button1.jpg").toURI().toString());
                    ImageView iv=new ImageView();
                    iv.setImage(pauseImage);
                    iv.setFitWidth(35);
                    iv.setFitHeight(35);
                    iv.setPreserveRatio(true);
                    iv.setSmooth(true);
                    iv.setCache(true);
                    playButton.setGraphic(iv);
                }    
            }
        });
        mp.setOnPaused(new Runnable() {

            @Override
            public void run() {
                Image playImage=new Image(new File("C:\\Users\\Smart User\\Desktop\\playbutton.jpg").toURI().toString());
                ImageView iv=new ImageView();
                iv.setImage(playImage);
                iv.setFitWidth(35);
                iv.setFitHeight(35);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                playButton.setGraphic(iv);
            }
        });
        /*mp.setOnReady(new Runnable() {

            @Override
            public void run() {
                try
                {
                   duration=mp.getMedia().getDuration();
                   System.out.println(duration);
                   updateValues(); 
                }    
                catch(Exception e)
                {
                    System.out.println("exception with duration");
                }    
            }
        });*/
        
        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE:1);
        mp.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                Image playImage=new Image(new File("C:\\Users\\Smart User\\Desktop\\playbutton.jpg").toURI().toString());
                ImageView iv=new ImageView();
                iv.setImage(playImage);
                iv.setFitWidth(40);
                iv.setFitHeight(40);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                playButton.setGraphic(iv);
                stopRequested=true;
                atEndOfMedia=true;
            }
        });
        mediaBar.getChildren().add(playButton);
        Label spacer=new Label(" ");
        mediaBar.getChildren().add(spacer);
        Label timeLabel=new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);
        timeSlider=new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        timeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(timeSlider.isValueChanging())
                mp.seek(duration.multiply(timeSlider.getValue()/100.0));
            }
        });
        mediaBar.getChildren().add(timeSlider);
        playTime=new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);
        Label volumeLabel=new Label("Volume: ");
        mediaBar.getChildren().add(volumeLabel);
        volumeSlider=new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(volumeSlider.isValueChanging())
                mp.setVolume(volumeSlider.getValue()/100.0);
            }
        });
           mediaBar.getChildren().add(volumeSlider);
           setBottom(mediaBar);
   }
   protected void updateValues()throws Exception
   {
       if(playTime!=null && timeSlider!=null && volumeSlider!=null)
       {
            Platform.runLater(new Runnable() {

               @Override
               public void run() {
                   Duration currentTime=mp.getCurrentTime();
                   System.out.println(currentTime);
                   playTime.setText(formatTime(currentTime,duration));
                   timeSlider.setDisable(duration.isUnknown());
                   if(!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging())
                   {
                       timeSlider.setValue(currentTime.divide(duration).toMillis()*100.0);
                   }
                   if(volumeSlider.isValueChanging())
                   {
                       volumeSlider.setValue((int)Math.round(mp.getVolume()*100));
                   }     
               }
           });
       }     
   }
   private static String formatTime(Duration elapsed,Duration duration)
   {
       int intElapsed=(int)Math.floor(elapsed.toSeconds());
       int elapsedHours=intElapsed/(60*60);
       if(elapsedHours>0)
       {
           intElapsed-=elapsedHours*60*60;
       }
       int elapsedMinutes=intElapsed/60;
       int elapsedSeconds=intElapsed-elapsedHours*60*60-elapsedMinutes*60;
       if(duration.greaterThan(Duration.ZERO))
       {
           int intDuration=(int)Math.floor(duration.toSeconds());
           int durationHours=intDuration/(60*60);
           if(durationHours>0)
           {
               intDuration-=durationHours*60*60;
           }
           int durationMinutes=intDuration/60;
           int durationSeconds=intDuration-(durationHours*60*60)-durationMinutes*60;
           if(durationHours>0)
           {
               return String.format("%d:%02d:%02d/%d:%02d:%02d",elapsedHours,elapsedMinutes,elapsedSeconds,durationHours,durationMinutes,durationSeconds);
           } 
           else
           {
               return String.format("%02d:%02d/%02d:%02d",elapsedMinutes,elapsedSeconds,durationMinutes,durationSeconds);
           }    
       }
       else
       {
           if(elapsedHours>0)
           {
               return String.format("%02d:%02d:%02d",elapsedHours,elapsedMinutes,elapsedSeconds);
           }
           else
           {
               return String.format("%02d:%02d",elapsedMinutes,elapsedSeconds);
           }    
       }    
   }
  
}
