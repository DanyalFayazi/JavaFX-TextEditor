package TextEditor;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage myStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View.fxml"));

        Parent root = fxmlLoader.load();
        EditorController controller = fxmlLoader.getController();
        controller.Initialization(myStage);
        myStage.setTitle("untitled");
        myStage.setScene(new Scene(root, 700, 500));
        myStage.show();
    }
}
