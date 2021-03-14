package controllers;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;

public class MenuController {
    public Button playButton, exitButton;

    public void playButtonOnClick() throws IOException {
        Scene scene = playButton.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("/views/players.fxml"));
        scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/resources/fontstyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void exitButtonOnClick() {
        Platform.exit();
    }
}
