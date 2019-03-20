
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class Effects2 extends Application
{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("MemoriamMedia");
        Group root=new Group();
        Scene scene=new Scene(root,400,400);
        BorderPane pane=new BorderPane();
        final ToggleGroup group=new ToggleGroup();
        ToggleButton tb1=new ToggleButton("Music");
        tb1.setToggleGroup(group);
        ToggleButton tb2=new ToggleButton("Video");
        tb2.setToggleGroup(group);
        ToggleButton tb3=new ToggleButton("Pictures");
        tb3.setToggleGroup(group);
        tb1.setUserData(Color.LIGHTGREEN);
        tb2.setUserData(Color.LIGHTSKYBLUE);
        tb3.setUserData(Color.BLACK);
        final Rectangle rect=new Rectangle(145,50);
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                if(t1==null)
                rect.setFill(Color.WHITE);
                else
                rect.setFill((Color)group.getSelectedToggle().getUserData());
            }
        });
        HBox hbox=new HBox(16);
        hbox.setPrefWidth(400);
        HBox.setHgrow(tb1, Priority.ALWAYS);
        HBox.setHgrow(tb2, Priority.ALWAYS);
        HBox.setHgrow(tb3, Priority.ALWAYS);
        hbox.getChildren().addAll(tb1,tb2,tb3);
        pane.setTop(hbox);
        scene.setRoot(pane);
        stage.setScene(scene);
        stage.show();
    }
   
}
