
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaPlayer;
import com.mpatric.mp3agic.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;

public class BuildGraphics extends BorderPane{
    private MediaPlayer mp;
    private ID3v2 id3v2Tag;
    ScrollPane sp=new ScrollPane();
    private Stage stage;
    private Duration d;
    //Group root;
    public BuildGraphics(MediaPlayer player,ID3v2 id3,Stage st,Duration dn)throws Exception
    {
        this.mp=player;
        this.id3v2Tag=id3;
        this.stage=st;
        this.d=dn;
        //this.root=rmain;
        sp.setPrefSize(600,300);
        HBox hb1=new HBox(16);
        hb1.setPrefWidth(600);
        hb1.setPrefHeight(25);
        hb1.setStyle("-fx-border-style: solid; -fx-border-width: 1; -fx-border-color: black; -fx-background-color: #336699");
        ToggleGroup group=new ToggleGroup();
        ToggleButton tb1=new ToggleButton("Music");
        tb1.setToggleGroup(group);
        hb1.getChildren().add(tb1);
        setTop(hb1);
        tb1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                
                final Hyperlink hp=new Hyperlink(id3v2Tag.getTitle()+"\n"+id3v2Tag.getArtist());
                hp.setAlignment(Pos.CENTER_LEFT);
                
                HBox hb2=new HBox();
                //sp.setContent(hp);
               /* String artist=id3v2Tag.getArtist();
                Label label=new Label(artist);
                label.setWrapText(true);
                Label label1=new Label();
                
                label1.setAlignment(Pos.BOTTOM_CENTER);
                label.setAlignment(Pos.BOTTOM_LEFT);*/
                hb2.getChildren().addAll(hp);
                
                sp.setContent(hb2);
                /*Separator sep=new Separator();
                sep.setPrefWidth(10);
                sep.setOrientation(Orientation.VERTICAL);
                sp.setContent(sep);
                sp.setContent(label*/
                setCenter(sp);
                hp.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent t) {
                        final HBox hb3=new HBox(16);
                        hb3.setPrefWidth(600);
                        hb3.setPrefHeight(100);
                        byte[] imageData=id3v2Tag.getAlbumImage();
                        try
                        {
                            
                            BufferedImage img=ImageIO.read(new ByteArrayInputStream(imageData));
                            final ImageView iv=new ImageView();
                            final Image image=SwingFXUtils.toFXImage(img, null);
                            
                            iv.setImage(image);
                            iv.setFitWidth(600);
                            iv.setFitHeight(100);
                            iv.setPreserveRatio(true);
                            iv.setSmooth(true);
                            iv.setOpacity(1.0);
                            iv.setCache(true);
                             final Label label1=new Label(id3v2Tag.getArtist().toUpperCase()+"\n"+id3v2Tag.getTitle()+"\n"+id3v2Tag.getAlbum()+"\n"+id3v2Tag.getYear());
                            label1.setWrapText(true);
                            final Button play=new Button("Play");
                            play.setAlignment(Pos.BOTTOM_RIGHT);
                            play.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent t) {
                                    try
                                    {
                                        MediaStarter obj=new MediaStarter(stage,mp,id3v2Tag,d);
                                        System.out.println("Reached1");
                                        stage.close();
                                        //obj.starter();
                                        
                                    }  
                                    catch(Exception e)
                                    {
                                        ;
                                    }    
                                }
                            });
                            final Button artists=new Button("Search Artist");
                            
                            artists.setAlignment(Pos.BOTTOM_RIGHT);
                            final Button close=new Button();
                            Image image1=new Image(new File("C:\\Users\\Smart User\\Desktop\\close1.jpg").toURI().toString());
                            ImageView iv2=new ImageView();
                            iv2.setImage(image1);
                            iv2.setFitWidth(20);
                            iv2.setPreserveRatio(true);
                            iv2.setSmooth(true);
                            iv2.setCache(true);
                            close.setGraphic(iv2);
                            //hb3.setSpacing(50);
                            close.setAlignment(Pos.BOTTOM_CENTER);
                            close.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent t) {
                                    hb3.getChildren().removeAll(iv,label1,play,artists,close);
                                    hp.setVisited(false);
                                }
                            });
                            hb3.getChildren().addAll(iv,label1,play,artists,close);
                            setBottom(hb3);
                        } 
                        catch(Exception e)
                        {
                           Image im1=new Image(new File("C:\\Users\\Smart User\\Desktop\\default artwork.jpg").toURI().toString());
                           final ImageView iv=new ImageView();
                           iv.setImage(im1);
                           iv.setFitWidth(600);
                           iv.setFitHeight(100);
                           iv.setPreserveRatio(true);
                           iv.setSmooth(true);
                           iv.setOpacity(1.0);
                           iv.setCache(true);
                           final Label label1=new Label(id3v2Tag.getArtist().toUpperCase()+"\n"+id3v2Tag.getTitle()+"\n"+id3v2Tag.getAlbum()+"\n"+id3v2Tag.getYear());
                            label1.setWrapText(true);
                            final Button play=new Button("Play");
                            play.setAlignment(Pos.BOTTOM_RIGHT);
                            play.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent t) {
                                    try
                                    {
                                        MediaStarter obj=new MediaStarter(stage,mp,id3v2Tag,d);
                                        System.out.println("Reached1");
                                        stage.close();
                                        //obj.starter();
                                        
                                    }  
                                    catch(Exception e)
                                    {
                                        ;
                                    }    
                                }
                            });
                            final Button artists=new Button("Search Artist");
                            
                            artists.setAlignment(Pos.BOTTOM_RIGHT);
                            final Button close=new Button();
                            Image image1=new Image(new File("C:\\Users\\Smart User\\Desktop\\close1.jpg").toURI().toString());
                            ImageView iv2=new ImageView();
                            iv2.setImage(image1);
                            iv2.setFitWidth(20);
                            iv2.setPreserveRatio(true);
                            iv2.setSmooth(true);
                            iv2.setCache(true);
                            close.setGraphic(iv2);
                            //hb3.setSpacing(50);
                            close.setAlignment(Pos.BOTTOM_CENTER);
                            close.setOnAction(new EventHandler<ActionEvent>() {

                                @Override
                                public void handle(ActionEvent t) {
                                    hb3.getChildren().removeAll(iv,label1,play,artists,close);
                                    hp.setVisited(false);
                                }
                            });
                            hb3.getChildren().addAll(iv,label1,play,artists,close);
                            setBottom(hb3);
                        }    
                    }
                });
            }
        });
    }        
    
}
