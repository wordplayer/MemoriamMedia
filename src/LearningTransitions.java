
//import javafx.animation.Animation;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LearningTransitions extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MemoriamMedia");
        //stage.setWidth(1000);
        //stage.setHeight(600);
        stage.setFullScreen(true);
        //System.out.println(stage.getWidth()+"x"+stage.getHeight());
        Group root=new Group();
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String time=sdf.format(cal.getTime());
        Label label=new Label(time);
        label.setStyle("-fx-font-family: Helvetica;-fx-font-size:18;");
        label.setAlignment(Pos.TOP_CENTER);
        Rectangle rect=new Rectangle(25,25,50,50);
        rect.setArcHeight(40);
        rect.setArcWidth(40);
        rect.setFill(Color.BLACK);
        rect.setTranslateX(50);
        rect.setTranslateY(50);
        root.getChildren().addAll(rect,label);
        FadeTransition ft=new FadeTransition(Duration.millis(1000),rect);
        ft.setFromValue(1.0);
        ft.setToValue(0.3);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        TranslateTransition tr=new TranslateTransition(Duration.millis(2000),rect);
        tr.setFromX(50);
        tr.setToX(375);
        tr.setCycleCount(1);
        tr.setAutoReverse(true);
        RotateTransition rt=new RotateTransition(Duration.millis(2000),rect);
        rt.setByAngle(180.0);
        rt.setCycleCount(4);
        rt.setAutoReverse(false);
        ScaleTransition st=new ScaleTransition(Duration.millis(2000),rect);
        st.setFromX(1);
        st.setFromY(1);
        st.setToX(2);
        st.setToY(2);
        st.setCycleCount(1);
        st.setAutoReverse(true);
        SequentialTransition seq=new SequentialTransition();
        seq.getChildren().addAll(ft,tr,rt,st);
        seq.setCycleCount(Timeline.INDEFINITE);
        seq.setAutoReverse(true);
        seq.play();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
 }
