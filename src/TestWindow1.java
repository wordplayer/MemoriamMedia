import javafx.scene.media.MediaPlayer;
import com.mpatric.mp3agic.ID3v2;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import javax.imageio.ImageIO;


public class TestWindow1 extends BorderPane {
    ID3v2 id3v2Tag;
    MediaPlayer mp;
    StackPane layout;
    boolean repeat=false;
    boolean stopRequested=false;
    boolean atEndOfMedia=false;
    Slider timeSlider;
    Slider volumeSlider;
    Label playTime;
    Duration duration;
    public TestWindow1(ID3v2 id3,MediaPlayer player)throws Exception
    {
        this.id3v2Tag=id3;
        this.mp=player;
        HBox hbt=new HBox();
        hbt.setPrefHeight(12);
        hbt.setStyle("-fx-background-color: grey;");
        Label label=new Label("MemoriamMedia");
        label.setWrapText(true);
        label.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 14; -fx-text-fill: black;");
        hbt.getChildren().add(label);
        label.setAlignment(Pos.TOP_CENTER);
        HBox.setHgrow(hbt,Priority.ALWAYS);
        setTop(hbt);
        this.layout=new StackPane();
        byte imageData[]=id3v2Tag.getAlbumImage();
        try
        {
            BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
            Image image=SwingFXUtils.toFXImage(img, null);
            ImageView iv=new ImageView();
            iv.setImage(image);
            iv.setFitWidth(1000);
            iv.setFitHeight(600);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setOpacity(0.4);
            layout.getChildren().add(iv);
            setCenter(layout);
        }
        catch(Exception e)
        {
            Image image=new Image(new File("C:\\Users\\Smart User\\Desktop\\default artwork.jpg").toURI().toString());
            ImageView iv=new ImageView();
            iv.setImage(image);
            iv.setFitWidth(1000);
            iv.setFitHeight(600);
            iv.setPreserveRatio(true);
            iv.setSmooth(true);
            iv.setCache(true);
            iv.setOpacity(0.4);
            layout.getChildren().add(iv);
            setCenter(layout);
        }
        
        HBox hb[]=new HBox[3];
        for(int i=0;i<3;i++)
        {
            hb[i]=new HBox();
        }    
        //hb[0].setPrefWidth(100);
        //hb[0].setPrefHeight(100);
        String songdes=id3v2Tag.getArtist().toUpperCase()+"\n"+id3v2Tag.getTitle()+"\n"+id3v2Tag.getAlbum();
        System.out.println(songdes);
        Label labsong=new Label(songdes);
        labsong.setWrapText(true);
        labsong.setStyle("-fx-font-family:\"Helvetica\"; -fx-font-size: 18; -fx-text-fill: white");
        hb[0].getChildren().add(labsong);
        hb[0].setStyle("-fx-background-color: black;");
        HBox.setHgrow(hb[0], Priority.ALWAYS);
        final FadeTransition fin=new FadeTransition(Duration.millis(1000),labsong);
        fin.setFromValue(0.0);
        fin.setToValue(1.0);
        final FadeTransition fout=new FadeTransition(Duration.millis(1000),labsong);
        fout.setFromValue(1.0);
        fout.setToValue(0.0);
        FadeTransition finit=new FadeTransition(Duration.millis(5000),labsong);
        finit.setFromValue(1.0);
        finit.setToValue(0.0);
        finit.play();
        hb[0].setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fin.play();
            }
        });
        hb[0].setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fout.play();
            }
        });
        hb[0].setAlignment(Pos.BOTTOM_LEFT);
        hb[0].setPrefWidth(400);
        hb[1].setPadding(new Insets(5,10,5,10));
        hb[1].setStyle("-fx-background-color: black;");
        final Button button=new Button();
        button.setStyle("-fx-background-color: white;");
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                Status status=mp.getStatus();
                if(status==Status.UNKNOWN||status==Status.HALTED)
                {
                    return;
                }
                if(status==Status.PAUSED|| status==Status.READY || status==Status.STOPPED)
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
                    System.out.println("Exception onsidering time");
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
                    Image image=new Image(new File("C:\\Users\\Smart User\\Desktop\\pause button1.jpg").toURI().toString());
                    ImageView iv=new ImageView();
                    iv.setImage(image);
                    iv.setFitWidth(40);
                    iv.setFitHeight(40);
                    iv.setPreserveRatio(true);
                    iv.setSmooth(true);
                    iv.setCache(true);
                    button.setGraphic(iv);
                }    
            }
        });
        mp.setOnPaused(new Runnable() {

            @Override
            public void run() {
                Image image=new Image(new File("C:\\Users\\Smart User\\Desktop\\playbutton.jpg").toURI().toString());
                ImageView iv=new ImageView();
                iv.setImage(image);
                iv.setFitWidth(40);
                iv.setFitHeight(40);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                button.setGraphic(iv);
            }
        });
        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE:1);
        mp.setOnEndOfMedia(new Runnable() {

            @Override
            public void run() {
                Image image=new Image(new File("C:\\Users\\Smart User\\Desktop\\playbutton.jpg").toURI().toString());
                ImageView iv=new ImageView();
                iv.setImage(image);
                iv.setFitWidth(40);
                iv.setFitHeight(40);
                iv.setPreserveRatio(true);
                iv.setSmooth(true);
                iv.setCache(true);
                button.setGraphic(iv);
                stopRequested=true;
                atEndOfMedia=true;
            }
        });
        VBox vb[]=new VBox[2];
        for(int i=0;i<2;i++)
        {
            vb[i]=new VBox();
        }    
        //hb[1].getChildren().add(button);
        //hb[1].setAlignment(button,Pos.BOTTOM_CENTER);
        hb[1].setStyle("-fx-background-color: red;");
        //HBox.setHgrow(hb[1], Priority.ALWAYS);
        timeSlider=new Slider();
        //HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(750);
        timeSlider.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(timeSlider.isValueChanging())
                {
                    mp.seek(duration.multiply(timeSlider.getValue()/100.0));
                }    
            }
        });
        //hb[1].getChildren().add(timeSlider);
        vb[0].getChildren().add(button);
        vb[0].setStyle("-fx-background-color: blue;");
        vb[0].setAlignment(Pos.BOTTOM_CENTER);
        vb[1].getChildren().add(timeSlider);
        vb[1].setStyle("-fx-background-color: green;");
        vb[1].setAlignment(Pos.TOP_CENTER);
        hb[1].getChildren().addAll(vb[0],vb[1]);
        final FadeTransition fin1=new FadeTransition(Duration.millis(1000),hb[1]);
        fin1.setFromValue(0.0);
        fin1.setToValue(1.0);
        final FadeTransition fout1=new FadeTransition(Duration.millis(1000),hb[1]);
        fout1.setFromValue(1.0);
        fout1.setToValue(0.0);
        FadeTransition finit1=new FadeTransition(Duration.millis(5000),hb[1]);
        finit1.setFromValue(1.0);
        finit1.setToValue(0.0);
        finit1.play();
        hb[1].setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fin1.play();
            }
        });
        hb[1].setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                fout1.play();
            }
        });
        hb[1].setAlignment(Pos.BOTTOM_CENTER);
        hb[1].setPrefWidth(600);
        setBottom(hb[0]);
        setBottom(hb[1]);
    }
    protected void updateValues()throws Exception
    {
        if(volumeSlider!=null && timeSlider!=null && playTime!=null)
        {
            Platform.runLater(new Runnable() {

                @Override
                public void run() {
                    try
                    {
                        Duration currentTime=mp.getCurrentTime();
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
                    catch(Exception e)
                    {
                        System.out.println("Error!");
                    }   
                }
            });
        }    
    } 
    private static String formatTime(Duration elapsed, Duration duration)throws Exception
    {
        int intElapsed=(int)Math.floor(elapsed.toSeconds());
        int elapsedHours=intElapsed/(60*60);
        if(elapsedHours>0)
        {
            intElapsed-=elapsedHours*60*60;
        } 
        int elapsedMinutes=intElapsed/60;
        int elapsedSeconds=intElapsed-(elapsedHours*60*60)-(elapsedMinutes*60);
        if(duration.greaterThan(Duration.ZERO))
        {
            int intDuration=(int)Math.floor(duration.toSeconds());
            int durationHours=intDuration/(60*60);
            if(durationHours>0)
            {
                intDuration-=durationHours*60*60;
            }
            int durationMinutes=intDuration/60;
            int durationSeconds=intDuration-(durationHours*60*60)-(durationMinutes*60);
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

