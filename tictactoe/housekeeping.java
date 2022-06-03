package tictactoe;

public class housekeeping {

    /** prints table with a border around it */
    static void printBoard() {

        System.out.println("---------");
        for (char[] row : Main.board) {
            System.out.print("| ");
            for (char item : row) {
                System.out.print(item + " ");//print all the elements followed by a blank space
            }
            System.out.println("|");
        }
        System.out.println("---------\n");
    }

    /**
     * @param board array to be searched
     * @param key item to be counted
     * @return number of occurences of key
     */
    static int occurrences(char[][] board, char key) {
        int occurrence = 0;

        for (char[] row : board) {
            for (char item : row) {
                if (item == key) {
                    occurrence++;
                }
            }
        }
        return occurrence;
    }

    static void welcomeMessage() {
        System.out.printf("%s \n%s \n\n", "Welcome to tictactoe in java.",
        "To start enter three commands : start followed by two users, or exit by itself to end programme");

        System.out.printf("%s \n%s \n%s \n\n","Sample: start user easy",
        "You may choose from 3 levels of difficulty for the ai opponenet, easy medium or hard",
        "It is possible to play with two human users, two AIs or one of each.");

        System.out.printf("%s \n%s \n\n", 
        "A human user will have to input valid coordinates for a move, a row 1-3 then a col 1-3",
        "Sample: Enter the coordinates: 3 2");
    }
}
