
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Timelines extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setFullScreen(true);
        Group root=new Group();
        Rectangle rect=new Rectangle(100,50,100,50);
        rect.setFill(Color.RED);
        root.getChildren().add(rect);
        Timeline timeline=new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        KeyValue kv=new KeyValue(rect.xProperty(),300);
        KeyFrame kf=new KeyFrame(Duration.millis(1000),kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
