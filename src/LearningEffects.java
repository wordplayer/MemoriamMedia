
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class LearningEffects extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MemoriamMedia");
        Scene scene=new Scene(new Group(),400,300);
        Button button=new Button();
        button.setText("Accept");
        button.setFont(new Font("Tahoma",24));
        button.setEffect(new Reflection());
        final Timeline timeline=new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv=new KeyValue(button.opacityProperty(),0.0);
        final KeyFrame kf=new KeyFrame(Duration.millis(1000),kv);
        timeline.getKeyFrames().add(kf);
        ((Group)scene.getRoot()).getChildren().add(button);
        stage.setScene(scene);
        stage.show();
        timeline.play();
        //((Group)scene.getRoot()).getChildren().add(button);
        
        //stage.show();
        
        
    }
    
}
