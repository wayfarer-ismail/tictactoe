package tictactoe;

import java.util.*;
import static tictactoe.Main.*;

public class makeMove {

    /** loops until user inputs valid coordinates then makes a play */
    static void userPlay() {
        boolean valid = false;
        int row = 0;
        int col = 0;

        while (!valid) {
            System.out.print("Enter the coordinates: ");
            String[] input = scanner.nextLine().trim().split(" "); //read coordinates as a string first, and split it in two

            try { //attempt to convert string to integers
                row = Integer.parseInt(input[0]) - 1;
                col = Integer.parseInt(input[1]) - 1;

                if (Main.board[row][col] != ' ') { //if not empty
                    System.out.println("This cell is occupied, Choose another one.");
                } else {
                    valid = true; //continue with the programme, input is valid
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3.");
            }
        }

        //check if there are more Xs than Os and decide the letter to be used
        Main.board[row][col] = housekeeping.occurrences(Main.board, 'X') > housekeeping.occurrences(Main.board, 'O') ? 'O' :  'X';
    }

    /** chooses a move based on the difficulty passed as a parameter
     * @param mode difficulty level of the ai
     */
    static void aiPlay(String mode) {
        boolean valid = true;
        int[] coordinates = {-1, -1};
        int row = 0, col = 0;

        char playerCode = housekeeping.occurrences(Main.board, 'X') > housekeeping.occurrences(Main.board, 'O') ? 'O' :  'X';
        char opponentCode = playerCode == 'X' ? 'O' : 'X';

        if (Objects.equals(mode, "easy")) {
            valid = false; //trigger the while loop below for random move
        }
        else if (Objects.equals(mode, "medium")) {
            coordinates = winLogic.getWin(playerCode); //check for a winning move

            if (coordinates[0] == -1) { //if its invalid check opponent

                coordinates = winLogic.getWin(opponentCode); //check for opponents winning move to block it
                //if its not found trigger the while loop below for a random move
                if (coordinates[0] == -1) { 
                    valid = false;
                }
            }
        } else { //hard mode
            coordinates = MinMax.findBestMove(Main.board.clone());   
        }

        if (valid) {//if medium doesn't find a winning play or hard is chosen
            row = coordinates[0];
            col = coordinates[1];
        }

        while (!valid) { //keep choosing random positions
            Random random = new Random();
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (Main.board[row][col] == ' ') { //if empty
                valid = true; //continue with the programme, numbers are valid
            }
        }

        System.out.printf("ai difficulty level : %s \n", mode);
        Main.board[row][col] = playerCode;
    }
}