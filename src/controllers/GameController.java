package controllers;
import game.Categories;
import game.Category;
import game.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class GameController {
    public TableView table;
    public TableColumn categoryColumn;
    public ComboBox categorySelector;
    public Button rerollButton, confirmButton, submitButton;
    public Label playerLabel;
    public Rectangle r1, r2, r3, r4, r5;
    public CheckBox cb1, cb2, cb3, cb4, cb5;
    private ArrayList<Player> players = new ArrayList<>();
    private int numberOfPlayers;
    private ObservableList<Category> scores;
    private Rectangle[] rectangles;
    private CheckBox[] checkBoxes;
    private int roundCounter;
    private int rerollCounter;

    public void initData(ArrayList<Player> players) {
        this.players = players;
        numberOfPlayers = players.size();
        categoryColumn.setMinWidth(145);
        scores = FXCollections.observableArrayList();
        String[] playerValuesFields = {"firstPlayerValue", "secondPlayerValue", "thirdPlayerValue",
                "fourthPlayerValue"};
        ObservableList<String> options = FXCollections.observableArrayList(
                "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
                "Three of a kind", "Four of a kind", "Full house", "Small straight",
                "Large straight", "Yahtzee", "Chance"
        );
        rectangles = new Rectangle[] {r1, r2, r3, r4, r5};
        checkBoxes = new CheckBox[] {cb1, cb2, cb3, cb4, cb5};

        for (String categoryName : Categories.getCategories())
            scores.add(new Category(categoryName));

        categoryColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));

        for (int i = 0; i < numberOfPlayers; i++) {
            TableColumn column = new TableColumn(players.get(i).getName());
            column.setMinWidth(88);
            table.getColumns().add(column);
            column.setCellValueFactory(new PropertyValueFactory<>(playerValuesFields[i]));
        }

        table.setItems(scores);
        categorySelector.setItems(options);
        initGame();
    }

    private void initGame() {
        playerLabel.setText(players.get(0).getName() + "'s turn");
        submitButton.setDisable(true);
        players.get(0).roll();
        categorySelector.getSelectionModel().selectFirst();

        for (int i = 0; i < 5; i++)
            rectangles[i].setFill(new ImagePattern(players.get(0).getDicesPng()[i]));

        for (int i = 0; i < numberOfPlayers; i++)
            scores.get(13).setPlayerValue(i, 0);

        roundCounter = 0;
        rerollCounter = 0;
    }

    public void rerollButtonOnClick() {
        for (int i = 0; i < 5; i++) {
            if (checkBoxes[i].isSelected()) {
                players.get(roundCounter % numberOfPlayers).reroll(i);
                rectangles[i].setFill(new ImagePattern((players.get(roundCounter % numberOfPlayers).getDicesPng()[i])));
            }
        }

        rerollCounter++;
            if (rerollCounter == 2)
                rerollButton.setDisable(true);
    }

    public void confirmButtonOnClick() {
        int categoryIndex = categorySelector.getSelectionModel().getSelectedIndex();
        if (players.get(roundCounter % numberOfPlayers).getChosenCategories()[categoryIndex]) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Category already used!");
            alert.setContentText("Choose a not yet used category");
            alert.showAndWait();
        }
        else {
            players.get(roundCounter % numberOfPlayers).getChosenCategories()[categoryIndex] = true;
            submitButton.setDisable(false);
            confirmButton.setDisable(true);
        }
        rerollButton.setDisable(true);
    }

    public void submitButtonOnClick() {
        rerollButton.setDisable(false);
        rerollCounter = 0;

        for (int i = 0; i < 5; i++)
            checkBoxes[i].setSelected(false);

        String[] functionNames = {"aces", "twos", "threes", "fours", "fives", "sixes", "threeOfAKind",
                "fourOfAKind", "fullHouse", "smallStraight", "largeStraight", "yahtzee", "chance"};

        int chosenCategoryIndex = categorySelector.getSelectionModel().getSelectedIndex();
        int playerIndex = roundCounter % numberOfPlayers;

        try {
            Categories categories = new Categories();
            Method method = Categories.class.getMethod(functionNames[chosenCategoryIndex], int[].class);
            int score;
            score = (Integer) method.invoke(categories, players.get(playerIndex).getDices());
            scores.get(chosenCategoryIndex).setPlayerValue(playerIndex, score);
            scores.get(13).setPlayerValue(playerIndex, scores.get(13).getPlayerValue(playerIndex) + score);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (roundCounter == 12 * numberOfPlayers + numberOfPlayers - 1) {
            endGame();
        }
        else {
            roundCounter++;
            playerIndex = roundCounter % numberOfPlayers;
            playerLabel.setText(players.get(playerIndex).getName() + "'s turn");
            players.get(playerIndex).roll();

            for (int i = 0; i < 5; i++)
                rectangles[i].setFill(new ImagePattern(players.get(playerIndex).getDicesPng()[i]));

            confirmButton.setDisable(false);
            submitButton.setDisable(true);
        }
    }

    private void endGame() {
        int highscore = 0;
        String winner = "";
        int score;

        for (int i = 0; i < numberOfPlayers; i++) {
            score = scores.get(13).getPlayerValue(i);
            if (score > highscore) {
                highscore = score;
                winner = players.get(i).getName();
            }
            else if (score == highscore) {
                winner += ", " + players.get(i).getName();
            }
        }

        confirmButton.setDisable(true);
        submitButton.setDisable(true);
        rerollButton.setDisable(true);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText("And the winner is....");
        alert.setContentText(winner);
        alert.showAndWait();
    }
}
