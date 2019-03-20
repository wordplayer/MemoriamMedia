
import java.io.File;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MediaControl extends BorderPane{
    MediaPlayer mediaPlayer;
    MediaView mediaView;
    final boolean repeat=false;
    boolean stopRequested=false;
    boolean atEndOfMedia=false;
    Duration duration;
    Slider timeSlider;
    Label playTime;
    Slider volumeSlider;
    HBox mediaBar;
    Stage stage;
    public MediaControl(final MediaPlayer mp,Stage st)throws Exception
    {
        this.mediaPlayer=mp;
        this.stage=st;
        Button fullsc=new Button("Set Full Screen");
        fullsc.setPrefWidth(200);
        fullsc.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                stage.setFullScreen(true);
            }
        });
        HBox hb1=new HBox();
        hb1.getChildren().add(fullsc);
        FadeTransition f1=new FadeTransition(Duration.millis(5000),fullsc);
        f1.setFromValue(1.0);
        f1.setToValue(0.0);
        f1.play();
        final FadeTransition fin=new FadeTransition(Duration.millis(1000),fullsc);
        fin.setFromValue(0.0);
        fin.setToValue(1.0);
        final FadeTransition fout=new FadeTransition(Duration.millis(1000),fullsc);
        fout.setFromValue(1.0);
        fout.setToValue(0.0);
        fullsc.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fin.play();
            }
        });
        fullsc.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fout.play();
            }
        });
        hb1.setStyle("-fx-background-color: black;");
        setTop(hb1);
        setStyle("-fx-background-color: #bfc2c7;");
        HBox hb[]=new HBox[3];
        for(int i=0;i<3;i++)
        {
            hb[i]=new HBox();
        }
        hb[0].setStyle("-fx-background-color:black;");
        mediaView=new MediaView(this.mediaPlayer);
        Pane mvPane=new Pane(){
            
        };
        mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: black;");
        HBox.setHgrow(mediaView,Priority.ALWAYS);
        hb[1].getChildren().add(mvPane);
        HBox.setHgrow(hb[0],Priority.ALWAYS);
        HBox.setHgrow(hb[1],Priority.ALWAYS);
        HBox.setHgrow(hb[2],Priority.ALWAYS);
        hb[2].setStyle("-fx-background-color: black;");
        HBox hbm=new HBox();
        hbm.getChildren().addAll(hb[0],hb[1],hb[2]);
        hb[1].setAlignment(Pos.CENTER);
        //setLeft(hb[0]);
        //setRight(hb[2]);
        setCenter(hbm);
        mediaBar=new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5,10,5,10));
        mediaBar.setStyle("-fx-background-color: black;");
        BorderPane.setAlignment(mediaBar, Pos.CENTER);
        setStyle("-fx-background-color: black;");
        final Button playButton=new Button();
        playButton.setStyle("-fx-background-color: white;");
        playButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                Status status=mediaPlayer.getStatus();
                if(status==Status.UNKNOWN||status==Status.HALTED)
                {
                    return;
                } 
                if(status==Status.PAUSED||status==Status.READY||status==Status.STOPPED)
                {
                    if(atEndOfMedia)
                    {
                        mediaPlayer.seek(mediaPlayer.getStartTime());
                        atEndOfMedia=false;
                    }
                    mediaPlayer.play();
                }
                else
                {
                    mediaPlayer.pause();
                }    
            }
        });
        mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                try
                {    
                updateValues();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }    
            }
        });
        mediaPlayer.setOnPlaying(new Runnable() {

            @Override
            public void run() {
                if(stopRequested)
                {
                    mediaPlayer.pause();
                    stopRequested=false;
                } 
                else
                {
                    //playButton.setText("||");
                   Image pauseImage=new Image(new File("C:\\Users\\Smart User\\Desktop\\pause button1.jpg").toURI().toString());
                   ImageView iv=new ImageView();
                   iv.setImage(pauseImage);
                   iv.setFitWidth(40);
                   iv.setFitHeight(40);
                   iv.setPreserveRatio(true);
                   iv.setSmooth(true);
                   iv.setCache(true);
                   playButton.setGraphic(iv);
                   playButton.setPrefWidth(40);
                   playButton.setPrefHeight(40);
                }    
            }
        });
        mediaPlayer.setOnPaused(new Runnable() {

            @Override
            public void run() {
                //System.out.println("onPaused");
                //playButton.setText(">");
                 Image playImage=new Image(new File("C:\\Users\\Smart User\\Desktop\\playbutton.jpg").toURI().toString());
                    ImageView iv=new ImageView();
                    iv.setImage(playImage);
                    iv.setFitWidth(40);
                    iv.setFitHeight(40);
                    iv.setPreserveRatio(true);
                    iv.setSmooth(true);
                    iv.setCache(true);
                    playButton.setGraphic(iv);
                    playButton.setPrefWidth(40);
                    playButton.setPrefHeight(40);
            }
        });
        mediaPlayer.setOnReady(new Runnable() {

            @Override
            public void run() {
                try
                {    
                duration=mediaPlayer.getMedia().getDuration();
                updateValues();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }    
            }
        });
        mediaPlayer.setCycleCount(repeat ?MediaPlayer.INDEFINITE:1);
        mediaPlayer.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                /*if(!repeat)
                {
                    playButton.setText(">");
                    stopRequested=true;
                    atEndOfMedia=true;
                }*/
                stage.close();
            }
        });
        mediaBar.getChildren().add(playButton);
        Label spacer=new Label("   ");
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
                {
                    mediaPlayer.seek(duration.multiply(timeSlider.getValue()/100.0));
                }    
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
                {
                    mediaPlayer.setVolume(volumeSlider.getValue()/100.0);
                }    
            }
        });
        mediaBar.getChildren().add(volumeSlider);
        FadeTransition f11=new FadeTransition(Duration.millis(5000),mediaBar);
        f11.setFromValue(1.0);
        f11.setToValue(0.0);
        f11.play();
        final FadeTransition fin1=new FadeTransition(Duration.millis(1000),mediaBar);
        fin1.setFromValue(0.0);
        fin1.setToValue(1.0);
        final FadeTransition fout1=new FadeTransition(Duration.millis(1000),mediaBar);
        fout1.setFromValue(1.0);
        fout1.setToValue(0.0);
        mediaBar.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fin1.play();
            }
        });
        mediaBar.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fout1.play();
            }
        });
        HBox.setHgrow(mediaBar, Priority.ALWAYS);
        
        setBottom(mediaBar);
    }
    protected void updateValues()throws Exception
    {
        if(playTime!=null && timeSlider!=null && volumeSlider!=null)
        {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                   Duration currentTime=mediaPlayer.getCurrentTime();
                   playTime.setText(formatTime(currentTime,duration));
                   timeSlider.setDisable(duration.isUnknown());
                   if(!timeSlider.isDisabled() && duration.greaterThan(Duration.ZERO) && !timeSlider.isValueChanging())
                   {
                       timeSlider.setValue(currentTime.divide(duration).toMillis()*100.0);
                   }
                   if(!volumeSlider.isValueChanging())
                   {
                       volumeSlider.setValue((int)Math.round(mediaPlayer.getVolume()*100.0));
                   }    
                }
            });
        }     
    }
    private static String formatTime(Duration elapsed, Duration duration)
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
            int durationSeconds=intDuration-durationHours*60*60-durationMinutes*60;
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
                return String.format("%d:%02d:%02d",elapsedHours,elapsedMinutes,elapsedSeconds);
            }
            else
            {
                return String.format("%02d:%02d",elapsedMinutes,elapsedSeconds);
            }    
        }    
    }        
}
