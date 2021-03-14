package game;
import java.util.ArrayList;
import java.util.Arrays;

public class Categories {

    public static ArrayList<String> getCategories() {
        return new  ArrayList<String>(
                Arrays.asList("Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
                        "Three of a kind", "Four of a kind", "Full house", "Small straight",
                        "Large straight", "Yahtzee", "Chance", "Sum"));
    }

    public static int aces(int[] dice) {
        int result = 0;

        for (int i = 0; i < 5; i++)
            if (dice[i] == 1)
                result++;

        return result;
    }

    public static int twos(int[] dice) {
        int result = 0;

        for (int i = 0; i < 5; i++)
            if (dice[i] == 2)
                result += 2;

        return result;
    }

    public static int threes(int[] dice) {
        int result = 0;

        for (int i = 0; i < 5; i++)
            if (dice[i] == 3)
                result += 3;

        return result;
    }

    public static int fours(int[] dice) {
        int result = 0;

        for (int i = 0; i < 5; i++)
            if (dice[i] == 4)
                result += 4;

        return result;
    }

    public static int fives(int[] dice) {
        int result = 0;

        for (int i = 0; i < 5; i++)
            if (dice[i] == 5)
                result += 5;

        return result;
    }

    public static int sixes(int[] dice) {
        int result = 0;

        for (int i = 0; i < 5; i++)
            if (dice[i] == 6)
                result += 6;

        return result;
    }

    public static int threeOfAKind(int[] dice) {
        int result = 0;
        int counter;

        for (int i = 0; i < 5; i++) {
            counter = 0;
            for (int j = 0; j < 5; j++)
                if (j != i && dice[i] == dice[j])
                    counter++;

            if (counter >= 2) {
                for (int j = 0; j < 5; j++)
                    result += dice[j];
                return result;
            }
        }

        return result;
    }

    public static int fourOfAKind(int[] dice) {
        int result = 0;
        int counter;

        for (int i = 0; i < 5; i++) {
            counter = 0;
            for (int j = 0; j < 5; j++)
                if (j != i && dice[i] == dice[j]) {
                    counter++;
                    if (counter >= 3) {
                        for (int k = 0; k < 5; k++)
                            result += dice[k];

                        return result;
                    }
                }
        }

        return result;
    }

    public static int fullHouse(int[] dice) {
        int result = 0;
        int threesNum = 0;
        int counter, counter2;

        for (int i = 0; i < 5; i++) {
            counter = 0;
            for (int j = 0; j < 5; j++)
                if (j != i && dice[i] == dice[j]) {
                    counter++;
                    if (counter >= 2)
                        threesNum = dice[j];
                }
        }

        for (int i = 0; i < 5; i++) {
            counter2 = 0;

            for (int j = 0; j < 5; j++)
                if (j != i && dice[i] != threesNum && dice[i] == dice[j] && threesNum != 0) {
                    counter2++;
                    if (counter2 == 1)
                        result = 25;
                }
        }

        return result;
    }

    public static int smallStraight(int[] dice) {
        int result = 0;

        if (Utilities.contains(dice, 1) && Utilities.contains(dice, 2) &&
                Utilities.contains(dice, 3) && Utilities.contains(dice, 4))
            return 30;

        if (Utilities.contains(dice, 2) && Utilities.contains(dice, 3) &&
                Utilities.contains(dice, 4) && Utilities.contains(dice, 5))
            return 30;

        if (Utilities.contains(dice, 3) && Utilities.contains(dice, 4) &&
                Utilities.contains(dice, 5) && Utilities.contains(dice, 6))
            return 30;

        return result;
    }

    public static int largeStraight(int[] dice) {
        int result = 0;
        int counter = 0;

        Arrays.sort(dice);
        for (int i = 0; i < 4; i++)
            if (!(dice[i] == dice[i+1]))
                counter++;

        if (counter == 4)
            result = 40;

        return result;
    }

    public static int yahtzee(int[] dice) {
        int result = 0;
        int counter = 0;

        for (int i = 1; i < 5; i++)
            if (dice[0] == dice[i])
                counter++;

        if (counter == 4)
            result = 50;

        return result;
    }

    public static int chance(int[] dice) {
        int result = 0;

        for (int i = 0; i < 5; i++)
            result += dice[i];

        return result;
    }
}
