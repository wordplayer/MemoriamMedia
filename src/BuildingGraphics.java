
import com.mpatric.mp3agic.ID3v2;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javax.imageio.ImageIO;

public class BuildingGraphics extends BorderPane {
    private MediaPlayer mp;
    private ID3v2 id3v2Tag;
    public BuildingGraphics(MediaPlayer player,ID3v2 id3)
    {
        this.mp=player;
        this.id3v2Tag=id3;
        HBox hb1=new HBox(16);
        ToggleButton tb1=new ToggleButton("Music");
        hb1.setPrefWidth(500);
        hb1.setPrefHeight(7.5);
        hb1.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black");
        hb1.getChildren().add(tb1);
        setTop(hb1);
        tb1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try
                {
                    StackPane layout=new StackPane();
                    Hyperlink hp=new Hyperlink(id3v2Tag.getTitle());
                    hp.setAlignment(Pos.CENTER_LEFT);
                    Label label=new Label("\n"+id3v2Tag.getArtist());
                    label.setWrapText(true);
                    label.setAlignment(Pos.CENTER_LEFT);
                    
                    final HBox hb2=new HBox();
                    hb2.setPrefWidth(500);
                    hb2.setPrefHeight(200);
                    hb2.setStyle("-fx-border-style: solid; -fx-border-width:1; -fx-border-color: black;");
                    hp.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent t) {
                            try
                            {
                                mp.setAutoPlay(true);
                                String songdes="Now Playing:\n"+id3v2Tag.getArtist()+"\n"+id3v2Tag.getTitle()+"\n"+id3v2Tag.getAlbum();
                                Label label=new Label(songdes);
                                label.setWrapText(true);
                                label.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 14;-fx-text-fill: white;");
                                byte[] imageData=id3v2Tag.getAlbumImage();
                                BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
                                Image image=SwingFXUtils.toFXImage(img, null);
                                ImageView iv=new ImageView();
                                iv.setImage(image);
                                iv.setFitWidth(500);
                                iv.setFitHeight(200);
                                iv.setOpacity(0.4);
                                iv.setPreserveRatio(true);
                                iv.setSmooth(true);
                                iv.setCache(true);
                                StackPane layout1=new StackPane();
                                layout1.getChildren().setAll(iv,label);
                                hb2.getChildren().add(layout1);
                                setBottom(hb2);
                            } 
                            catch(Exception e)
                            {
                                ;
                            }
                         }
                     });
                    layout.getChildren().addAll(hp,label);
                    setCenter(layout);
                }
                catch(Exception e)
                {
                    ;
                }    
            }
        });
    }        
}
