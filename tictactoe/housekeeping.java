package tictactoe;

import static tictactoe.Main.*;

public class housekeeping {
    /**
     * prints table with a border around it
     * @param table game board to be printed
     */
    static void printArray() {

        System.out.println("---------");
        for (char[] row : Main.board) {
            System.out.print("| ");
            for (char item : row) {
                System.out.print(item + " ");//print all the elements followed by a blank space
            }
            System.out.println("|");
        }
        System.out.println("---------");
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
}
