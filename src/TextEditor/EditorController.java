package TextEditor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

//لطفا برای خروج به قسمت file رفته و بر روی close کلیک کنید

public class EditorController implements Initializable {

    @FXML
    private TextArea textArea;

    private Stage stage;
    private final FileChooser fileChooser = new FileChooser();



    public void Initialization(Stage myStage) {
        this.stage = myStage;
    }

    public void exit() {
        if (textArea.getText().isEmpty()) {
            Platform.exit();
            return;
        }

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Exit without saving?",
                ButtonType.YES,
                ButtonType.NO,
                ButtonType.CANCEL
        );

        alert.setTitle("Confirm");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Platform.exit();
        }
        if (alert.getResult() == ButtonType.NO) {
            save();
            Platform.exit();
        }
    }


    @FXML
    private void save() {
        try {
            fileChooser.setTitle("Save As");
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(savedText);
                out.write(textArea.getText());
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openFile() {
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            stage.setTitle(file.getName());
            textArea.clear();
            readText(file);
        }
    }


    // sets the textArea to the text of the opened file
    private void readText(File file) {
        String text;

        try (BufferedReader buffReader = new BufferedReader(new FileReader(file))) {
            while ((text = buffReader.readLine()) != null) {
                textArea.appendText(text + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void newFile() {
        textArea.clear();
    }


    public void about() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("About");
        alert.setHeaderText("text editor project designed by Danial Fayazi");
        alert.showAndWait();
    }



    public void fontSize(ActionEvent e) {
        String choice = ((CheckMenuItem) e.getSource()).getId();

        switch (choice) {
            case "small":
                textArea.setStyle("-fx-font-size: 10px");
                break;
            case "default":
                textArea.setStyle("-fx-font-size: 22px");
                break;
            case "large":
                textArea.setStyle("-fx-font-size: 44px");
                break;
            default:
                textArea.setStyle("-fx-font-size: 22px");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Text", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.obj"));
    }
}
