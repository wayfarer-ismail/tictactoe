package tictactoe;

// Java program to find the next optimal move for a player, This code is inspired by PrinciRaj1992 :
// https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/
public class MinMax extends makeMove{

    /**considers all the possible ways the game can go and returns the value of the sequence
     * @param board the playing board
     * @param depth how many virtual moves have been made so far
     * @param isMax used to alternate between simulated ai and user turns
     * @param player the ai's symbol
     * @param opponent the users symbol
     * @return value of a position in the board from -10 - 10
     */
    static int minimax(char[][] board, int depth, Boolean isMax, char player, char opponent)
    {
        // If Maximizer or minimiser has won the game return evaluated score
        if (winLogic.isWinner(board, player)) {
           return 10 - depth; 
        } else if (winLogic.isWinner(board, opponent)) {
            return depth - 10;
        } else if (housekeeping.occurrences(board, ' ') == 0) {
            return 0; // If there are no more moves and no winner then it is a tie
        }

        // If this is maximizer's move
        int best;
        if (isMax) {
            best = -1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == ' ') {
                        // Make the move
                        board[i][j] = player;

                        // Call minimax recursively and choose the maximum value
                        best = Math.max(best, minimax(board, depth + 1, !isMax, player, opponent));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
        } else { // If this is minimizer's move
            best = 1000;

            // Traverse all cells
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // Check if cell is empty
                    if (board[i][j] == ' ') {
                        // Make the move
                        board[i][j] = opponent;

                        // Call minimax recursively and choose the minimum value
                        best = Math.min(best, minimax(board, depth + 1, !isMax, player, opponent));

                        // Undo the move
                        board[i][j] = ' ';
                    }
                }
            }
        }
        return best;
    }

    /**
     * @param board playing board
     * @return coordinates of the optimum move based on minmax result
     */
    static int[] findBestMove(char[][] board)
    {
        char playerCode = housekeeping.occurrences(board, 'X') > housekeeping.occurrences(board, 'O') ? 'O' :  'X';
        char opponentCode = playerCode == 'X' ? 'O' : 'X'; //sets to the alternative char

        int bestVal = -1000;
        int[] bestMove = new int[2];

        // evaluate minimax function for all empty cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                // Check if cell is empty
                if (board[i][j] == ' ') {
                    
                    board[i][j] = playerCode; // Make the move

                    // compute the value of this move.
                    int moveVal = minimax(board, 0, false, playerCode, opponentCode);

                    board[i][j] = ' '; // Undo the move

                    if (moveVal > bestVal) { //if the current move is better than the best move, update best
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }
}
