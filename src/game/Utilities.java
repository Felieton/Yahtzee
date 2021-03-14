package game;
import javafx.scene.image.Image;

public class Utilities {

    public static Image getImage(int number) {
        return new Image("resources/png/" + "die" + number + ".png");
    }

    public static boolean contains(final int[] array, final int v) {
        for (int i : array)
            if(i == v)
                return true;

        return false;
    }
}
