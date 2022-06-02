package tictactoe;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        char[][] table = new char[3][3]; //the board to be used

        while(true) {

            String[] users = body.setUsers();

            housekeeping.initialiseBoard(table);

            body.playGame(table, users);

            winLogic.announceWinner(table);
        }
    }
}
