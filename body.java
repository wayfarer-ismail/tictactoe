import java.util.*;
import static tictactoe.Main.*;

public class body {
    /**
     * takes 3 commands from the input line and verifies they are valid
     * @return returns a String array of two user names
     */
    static String[] setUsers() {
        boolean valid = false;
        String[] userOptions = new String[]{ "easy", "hard", "medium", "user"};
        String[] users = new String[2];

        while(!valid) {
            System.out.print("Input command: ");
            String mode = scanner.next();
            users = Arrays.copyOf(scanner.nextLine().trim().split(" "), 2);

            if(Objects.equals(mode, "exit")) {
                System.exit(0);
            }

            try {//check the inputs are all valid
                valid = Arrays.binarySearch(userOptions, users[0]) >= 0 &&
                        Arrays.binarySearch(userOptions, users[1]) >= 0 &&
                        Objects.equals(mode, "start");
            } catch (NullPointerException e) {//triggered when less than three words are scanned
                System.out.println("Bad parameters!");
            }

            if (!valid) {
                System.out.println("Bad parameters!");
            }
        }
        return users;
    }

    /**
     * alternates between the two users letting them play a turn until a win or draw
     * @param table the playing field
     * @param users the two users playing the game
     */
    static void playGame(char[][] table, String[] users) {
        int i = 0;

        while (housekeeping.occurrences(table,' ') != 0) {

            if (Objects.equals(users[i], "user")) {
                makeMove.userPlay(table);
            } else {
                makeMove.aiPlay(table, users[i]);
            }

            housekeeping.printArray(table);

            if (winLogic.isWinner(table, 'O') || winLogic.isWinner(table, 'X')) { break; }

            i = i == 0? 1 : 0; //alternate between user 1 and 2
        }
    }
}
