
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class TestMenu extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane border=new BorderPane();
        Scene scene=new Scene(border);
        stage.setTitle("Test Hyperlink");
        stage.setWidth(600);
        stage.setHeight(500);
    }
}
