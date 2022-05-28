package tictactoe;

public class housekeeping {
    /**
     * takes in the cells inputted by user and places them in their respective position in the board
     * @param table the board that will be initialised
     */
    static void initialiseBoard(char[][] table) {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = ' '; //copy each letter of the string into the 2D array
            }
        }
        printArray(table);
    }

    /**
     * prints table with a border around it
     * @param table game board to be printed
     */
    static void printArray(char[][] table) {

        System.out.println("---------");
        for (char[] row : table) {
            System.out.print("| ");
            for (char item : row) {
                System.out.print(item + " ");//print all the elements followed by a blank space
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    /**
     * @param table array to be searched
     * @param key item to be counted
     * @return number of occurences of key
     */
    static int occurrences(char[][] table, char key) {
        int occurrence = 0;

        for (char[] row : table) {
            for (char item : row) {
                if (item == key) {
                    occurrence++;
                }
            }
        }
        return occurrence;
    }
}
