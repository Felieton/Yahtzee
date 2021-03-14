package controllers;
import game.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import java.io.IOException;
import java.util.ArrayList;

public class PlayersController {
    public Button backToMenuButton, startGameButton;
    public ComboBox<Integer> playersSelector;
    public TextField playerOneTf, playerTwoTf, playerThreeTf, playerFourTf;
    private int numberOfPlayers;

    @FXML
    public void initialize() {
        ObservableList<Integer> options = FXCollections.observableArrayList(
                2, 3, 4
        );
        playersSelector.setItems(options);
        playersSelector.getSelectionModel().selectFirst();
        playerThreeTf.setDisable(true);
        playerFourTf.setDisable(true);
        numberOfPlayers = 2;
    }

    public void backToMenuButtonOnClick() throws IOException {
        Scene scene = backToMenuButton.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        Parent root = FXMLLoader.load(getClass().getResource("/Views/menu.fxml"));
        scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("/resources/fontstyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void selectorChosen() {
        numberOfPlayers = playersSelector.getSelectionModel().getSelectedItem();

        if (numberOfPlayers == 2) {
            playerThreeTf.setDisable(true);
            playerFourTf.setDisable(true);
        }
        else if (numberOfPlayers == 3) {
            playerThreeTf.setDisable(false);
            playerFourTf.setDisable(true);
        }
        else {
            playerThreeTf.setDisable(false);
            playerFourTf.setDisable(false);
        }
    }

    public void startGameButtonOnClick() throws IOException {
        ArrayList<TextField> textFields = new ArrayList<TextField>() {{
                add(playerOneTf);
                add(playerTwoTf);
                add(playerThreeTf);
                add(playerFourTf);
            }
        };
        ArrayList<Player> players = new ArrayList<>();

        for (int i = 0; i < numberOfPlayers; i++)
            players.add(new Player(textFields.get(i).getText()));

        Scene scene = startGameButton.getScene();
        Window window = scene.getWindow();
        Stage stage = (Stage) window;
        FXMLLoader root = new FXMLLoader(getClass().getResource("/Views/game.fxml"));
        int width = 800 + numberOfPlayers * 88;
        scene = new Scene(root.load(), width, 420);
        scene.getStylesheets().add(getClass().getResource("/resources/fontstyle.css").toExternalForm());
        stage.setScene(scene);
        GameController gameController = root.getController();
        gameController.initData(players);
        stage.show();
    }
}
