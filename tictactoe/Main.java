package tictactoe;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    protected static char[][] board = new char[3][3]; //the board to be used

    public static void main(String[] args) {

        while(true) {

            for (char[] chars : board) { Arrays.fill(chars, ' '); } //fill array with blank spaces

            String[] users = body.setUsers();

            body.playGame(users);

            winLogic.announceWinner(users);
        }
    }
}
