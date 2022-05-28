package tictactoe;

import java.util.Arrays;

public class winLogic {
    /**
     * @param table array to be searched
     * @param item letter to be checked for winning formation
     * @return whether or not a winning formation was found
     */
    static boolean isWinner(char[][] table, char item){
        boolean win = false;

        for (int i = 0; i < table.length; i++) {
            win = win || Arrays.equals(table[i], new char[]{item, item, item}); //check rows
            win = win || table[0][i] == item && table[1][i] == item && table[2][i] == item; //check columns
        }

        win = win || table[0][0] == item && table[1][1] == item && table[2][2] == item; //diagonal from top left
        win = win || table[2][0] == item && table[1][1] == item && table[0][2] == item; //diagonal from top right

        return win;
    }

    /** checks all two in a row combinations for a win
     * @param table the playing field
     * @param item the letter to be checked for winning
     * @return the coordinates of a winning location
     */
    static int[] getWin(char[][] table, char item) {
        char[] items = new char[]{item, item, ' '};

        for (int i = 0; i < table.length; i++) { // check all row and column combinations
            for (int j = 0; j < table.length; j++) {
                //if(table[i][j] == item && table[i][(1 + j) % 3] == item && table[i][(2 + j) % 3] == ' '){
                if(Arrays.equals(new char[]{table[i][j], table[i][(1 + j) % 3],  table[i][(2 + j) % 3]}, items)) {
                    return new int[]{i, (2 + j) % 3}; // check the rows
                } else if (table[j][i] == item && table[(1 + j) % 3][i] == item && table[(2 + j) % 3][i] == ' '){
                    return new int[]{(2 + j) % 3, i}; // check the columns
                }
            }
        }

        for (int i = 0; i < table.length; i++) { //checks 3 positions for both diagonals
            if (table[i][i] == ' ' && table[(1 + i) % 3][(1 + i) % 3] == item && table[(2 + i) % 3][(2 + i) % 3] == item) { //X _ X
                return new int[]{i, i};
            } else if (table[2 - i][i] == item && table[(4 - i) % 3][(1 + i) % 3] == item && table[(3-i) % 3][(2 + i) % 3] == ' ') {//diagonal from top right
                return new int[]{(3-i) % 3, (2 + i) % 3};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * prints the correct winning state at the end of the game
     * @param table the board used in the game
     */
    static void announceWinner(char[][] table){
        if (isWinner(table, 'X')) {
            System.out.println("X wins");
        } else if (isWinner(table, 'O')) {
            System.out.println("O wins");
        } else {
            System.out.println("Draw");
        }
    }
}
