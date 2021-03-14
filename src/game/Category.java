package game;
import javafx.beans.property.SimpleStringProperty;

public class Category {
    private String name;
    private SimpleStringProperty firstPlayerValue;
    private SimpleStringProperty secondPlayerValue;
    private SimpleStringProperty thirdPlayerValue;
    private SimpleStringProperty fourthPlayerValue;

    public Category(String name) {
        this.name = name;
        this.firstPlayerValue = new SimpleStringProperty("");
        this.secondPlayerValue = new SimpleStringProperty("");
        this.thirdPlayerValue = new SimpleStringProperty("");
        this.fourthPlayerValue = new SimpleStringProperty("");
    }

    public String getFirstPlayerValue() {
        return firstPlayerValue.get();
    }

    public SimpleStringProperty firstPlayerValueProperty() {
        return firstPlayerValue;
    }

    public void setFirstPlayerValue(int firstPlayerValue) {
        this.firstPlayerValue.set(Integer.toString(firstPlayerValue));
    }

    public String getSecondPlayerValue() {
        return secondPlayerValue.get();
    }

    public SimpleStringProperty secondPlayerValueProperty() {
        return secondPlayerValue;
    }

    public void setSecondPlayerValue(int secondPlayerValue) {
        this.secondPlayerValue.set(Integer.toString(secondPlayerValue));
    }

    public String getThirdPlayerValue() {
        return thirdPlayerValue.get();
    }

    public SimpleStringProperty thirdPlayerValueProperty() {
        return thirdPlayerValue;
    }

    public void setThirdPlayerValue(int thirdPlayerValue) {
        this.thirdPlayerValue.set(Integer.toString(thirdPlayerValue));
    }

    public String getFourthPlayerValue() {
        return fourthPlayerValue.get();
    }

    public SimpleStringProperty fourthPlayerValueProperty() {
        return fourthPlayerValue;
    }

    public void setFourthPlayerValue(int fourthPlayerValue) {
        this.fourthPlayerValue.set(Integer.toString(fourthPlayerValue));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerValue(int indexOfPlayer, int value) {
        switch (indexOfPlayer) {
            case 0:
                setFirstPlayerValue(value);
                break;
            case 1:
                setSecondPlayerValue(value);
                break;
            case 2:
                setThirdPlayerValue(value);
                break;
            case 3:
                setFourthPlayerValue(value);
                break;
        }
    }

    public int getPlayerValue(int indexOfPlayer) {
        switch (indexOfPlayer) {
            case 0:
                return Integer.parseInt(getFirstPlayerValue());
            case 1:
                return Integer.parseInt(getSecondPlayerValue());
            case 2:
                return Integer.parseInt(getThirdPlayerValue());
            case 3:
                return Integer.parseInt(getFourthPlayerValue());
            default:
                return 0;
        }
    }
}
