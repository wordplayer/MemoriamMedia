
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.Clipboard;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;


public class TestingTextField extends Application{
    public static void main(String args[])throws Exception
    {
        launch(args);
    }        
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("ChatBuddy");
        stage.setWidth(800);
        stage.setHeight(600);
        Group root=new Group();
        final TextField text=new TextField();
        text.setPrefColumnCount(20);
        text.getText();
        HBox hb=new HBox();
        hb.getChildren().add(text);
        hb.setAlignment(Pos.BOTTOM_CENTER);
        HBox.setHgrow(text,Priority.ALWAYS);
        HBox.setHgrow(hb, Priority.ALWAYS);
        final HBox hbc=new HBox();
        hbc.setAlignment(Pos.TOP_CENTER);
        final Clipboard clip=Clipboard.getSystemClipboard();
        root.getChildren().addAll(hb,hbc);
        text.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                   text.cut();
                   String content=clip.getString();
                   System.out.println(content);
                   text.clear();
                   Label lab=new Label(content);
                   lab.setStyle("-fx-font-family: \"Helvetica\"; -fx-font-size: 16; -fx-text-fill: black;");
                   lab.setWrapText(true);
                   hbc.getChildren().add(lab);
            }
        });
        Scene scene=new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();
    }
    
}
