package tictactoe;

import java.util.*;
import static tictactoe.Main.*;

public class body {
    /**
     * takes 3 commands from the input line and verifies they are valid
     * @return returns a String array of two user names
     */
    static String[] setUsers() {
        boolean valid = false;
        String[] userOptions = { "easy", "hard", "medium", "user"};
        String[] users = new String[2];

        while(!valid) {
            System.out.print("Input command: ");
            String mode = scanner.next(); //read start or exit
            
            if(Objects.equals(mode, "exit")) {
                System.out.print("\nGoodbye.");
                System.exit(0);
            }
            
            try {//check the inputs are all valid
                users = scanner.nextLine().trim().split(" "); //read the two users

                valid = Arrays.binarySearch(userOptions, users[0]) >= 0 &&
                        Arrays.binarySearch(userOptions, users[1]) >= 0 &&
                        Objects.equals(mode, "start");
            } catch (NullPointerException e) {//triggered when less than three words are scanned
                System.out.println("");
            }

            if (!valid) {
                System.out.println("Bad parameters!");
            }
        }
        return users;
    }

    /**
     * alternates between the two users letting them play a turn until a win or draw
     * @param users the two users playing the game
     */
    static void playGame(String[] users) {
        int i = 0;

        while (housekeeping.occurrences(Main.board,' ') != 0) { //while there are empty places available

            if (Objects.equals(users[i], "user")) {
                makeMove.userPlay();
            } else {
                makeMove.aiPlay(users[i]);
            }

            housekeeping.printBoard();

            if (winLogic.isWinner(Main.board, 'O') || winLogic.isWinner(Main.board, 'X')) { break; }

            i = i == 0? 1 : 0; //alternate between user 1 and 2
        }
    }
}
