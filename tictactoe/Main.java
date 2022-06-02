package tictactoe;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    protected static char[][] table = new char[3][3]; //the board to be used

    public static void main(String[] args) {

        while(true) {

            for (char[] chars : table) { Arrays.fill(chars, ' '); } //fill array with blank spaces

            String[] users = body.setUsers();

            body.playGame(table, users);

            winLogic.announceWinner(table, users);
        }
    }
}
