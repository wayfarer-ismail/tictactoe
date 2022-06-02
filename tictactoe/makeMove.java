package tictactoe;

import java.util.*;

import static tictactoe.Main.*;

public class makeMove {

    /**
     * user inputs valid coordinates and makes a play
     * @param table the play will be made on this board
     */
    static void userPlay(char[][] table) {
        boolean valid = false;
        int row = 0;
        int col = 0;

        while (!valid) {
            System.out.print("Enter the coordinates: ");
            String[] input = scanner.nextLine().trim().split(" "); //read coordinates as a string first, and split it in two

            try { //attempt to convert string to integers
                row = Integer.parseInt(input[0]) - 1;
                col = Integer.parseInt(input[1]) - 1;

                if (table[row][col] != ' ') { //if not empty
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    valid = true; //continue with the programme, input is valid
                }
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }

        //check if there are more Xs than Os and decide the letter to be used
        table[row][col] = housekeeping.occurrences(table, 'X') > housekeeping.occurrences(table, 'O') ? 'O' :  'X';
    }

    /** chooses a move based on the difficulty passed as a parameter
     * @param table the playing field
     * @param mode difficulty level of the ai
     */
    static void aiPlay(char[][] table, String mode) {
        boolean valid = true;
        int row = 0;
        int col = 0;
        char playerCode = housekeeping.occurrences(table, 'X') > housekeeping.occurrences(table, 'O') ? 'O' :  'X';
        char opponentCode = playerCode == 'X' ? 'O' : 'X';

        if (Objects.equals(mode, "easy")) {
            valid = false; //trigger the while loop below for random move
        }
        else if (Objects.equals(mode, "medium")) {
            int[] coordinates = winLogic.getWin(table, playerCode);

            if (coordinates[0] == -1) { //if its invalid check opponent
                coordinates = winLogic.getWin(table, opponentCode);
                if (coordinates[0] == -1) { //if its invalid trigger the while loop for a random move
                    valid = false;
                }
            }
            row = coordinates[0];
            col = coordinates[1];
        }

        while (!valid) {
            Random random = new Random();
            row = random.nextInt(3);
            col = random.nextInt(3);

            if (table[row][col] == ' ') { //if empty
                valid = true; //continue with the programme, numbers are valid
            }
        }

        System.out.printf("Making move level \"%s\"\n", mode);
        table[row][col] = playerCode;
    }
}