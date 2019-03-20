
import java.io.File;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPaneBuilder;
import javafx.stage.Stage;


public class ToggleButtonImage extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        ToggleButton button=new ToggleButton();
        Image pause=new Image(new File("C:\\Users\\Smart User\\Desktop\\pause button1.jpg").toURI().toString());
        Image play=new Image(new File("C:\\Users\\Smart User\\Desktop\\play button.jpg").toURI().toString());
        ImageView toggleImage=new ImageView();
        button.setGraphic(toggleImage);
        button.setMaxSize(5,5);
        toggleImage.imageProperty().bind(Bindings.when(button.selectedProperty()).then(play).otherwise(pause));
        stage.setScene(new Scene(StackPaneBuilder.create().children(button).style("-fx-padding:12;-fx-background-color:cornsilk;").build()));
        stage.show();
    }
    
}
