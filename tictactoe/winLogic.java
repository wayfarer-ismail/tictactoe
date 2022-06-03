package tictactoe;

import java.util.*;

public class winLogic {
    /**
     * @param board playing board to be searched
     * @param item letter to be checked for winning formation
     * @return whether or not a winning formation was found
     */
    static boolean isWinner(char[][] board, char item){
        boolean win = false;

        for (int i = 0; i < board.length; i++) {
            win = win || Arrays.equals(board[i], new char[]{item, item, item}); //check rows
            win = win || board[0][i] == item && board[1][i] == item && board[2][i] == item; //check columns
        }

        win = win || board[0][0] == item && board[1][1] == item && board[2][2] == item; //diagonal from top left
        win = win || board[2][0] == item && board[1][1] == item && board[0][2] == item; //diagonal from top right

        return win;
    }

    /** checks all two in a row combinations for a win
     * @param item the letter to be checked for winning
     * @return the coordinates of a winning location, other wise {-1, -1}
     */
    static int[] getWin(char item) {

        for (int i = 0; i < Main.board.length; i++) { // check all row and column combinations
            for (int j = 0; j < Main.board.length; j++) {
                if (Main.board[i][j] == item && Main.board[i][(1 + j) % 3] == item && Main.board[i][(2 + j) % 3] == ' ') {
                    return new int[]{i, (2 + j) % 3}; // check the rows
                } else if (Main.board[j][i] == item && Main.board[(1 + j) % 3][i] == item && Main.board[(2 + j) % 3][i] == ' ') {
                    return new int[]{(2 + j) % 3, i}; // check the columns
                }
            }
        }

        for (int i = 0; i < Main.board.length; i++) { //checks 3 positions for both diagonals
            if (Main.board[i][i] == ' ' && Main.board[(1 + i) % 3][(1 + i) % 3] == item && Main.board[(2 + i) % 3][(2 + i) % 3] == item) { //X _ X
                return new int[]{i, i};
            } else if (Main.board[2 - i][i] == item && Main.board[(4 - i) % 3][(1 + i) % 3] == item && Main.board[(3-i) % 3][(2 + i) % 3] == ' ') {//diagonal from top right
                return new int[]{(3-i) % 3, (2 + i) % 3};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * prints the correct winning state at the end of the game
     * @param users a String array of the two players
     */
    static void announceWinner(String[] users){
        if (isWinner(Main.board, 'X')) {
            System.out.printf("%s as X wins \n\n", Objects.equals(users[0], "user") ? users[0] : "ai - " + users[0]);
        } else if (isWinner(Main.board, 'O')) {
            System.out.printf("%s as O wins\n\n", Objects.equals(users[1], "user") ? users[1] : "ai - " + users[1]);
        } else {
            System.out.println("Draw");
        }
    }
}
