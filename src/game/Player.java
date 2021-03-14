package game;
import javafx.scene.image.Image;

public class Player {
    private String name;
    private boolean [] chosenCategories;
    private int[] dices;
    private Image[] dicesPng;

    public Player(String name) {
        this.name = name;
        this.chosenCategories = new boolean[13];
        dices = new int[5];
        dicesPng = new Image[5];
    }

    public void roll() {
        for (int i = 0; i < 5; i++) {
            int number = (int) (Math.random() * 6) + 1;
            this.dices[i] = number;
            this.dicesPng[i] = Utilities.getImage(number);
        }
    }

    public void reroll(int index) {
        int number = (int) (Math.random() * 6) + 1;
        this.dices[index] = number;
        this.dicesPng[index] = Utilities.getImage(number);
    }

    public boolean[] getChosenCategories() {
        return chosenCategories;
    }

    public Image[] getDicesPng() {
        return dicesPng;
    }

    public int[] getDices() {
        return dices;
    }

    public String getName() {
        return name;
    }
}
