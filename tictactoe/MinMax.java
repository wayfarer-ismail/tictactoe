package tictactoe;

public class MinMax extends makeMove{
    // Java program to find the next optimal move for a player
    // This code is inspired by PrinciRaj1992 :
    // https://www.geeksforgeeks.org/minimax-algorithm-in-game-theory-set-3-tic-tac-toe-ai-finding-optimal-move/

    // This is the minimax function. It considers all
    // the possible ways the game can go and returns
    // the value of the board
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

    // This will return the best possible
    // move for the player
    static int[] findBestMove(char[][] board)
    {
        char playerCode = housekeeping.occurrences(board, 'X') > housekeeping.occurrences(board, 'O') ? 'O' :  'X';
        char opponentCode = playerCode == 'X' ? 'O' : 'X'; //sets to the alternative char

        int bestVal = -1000;
        int[] bestMove = new int[2];

        // Traverse all cells, evaluate minimax function for all
        // empty cells. And return the cell with optimal value.
        for (int i = 0; i < 3; i++) {
            System.out.printf("\n %d :", i);
            for (int j = 0; j < 3; j++) {
                // Check if cell is empty
                
                if (board[i][j] == ' ') {
                    // Make the move
                    board[i][j] = playerCode;

                    // compute evaluation function for this move.
                    int moveVal = minimax(board, 0, false, playerCode, opponentCode);
                    System.out.printf(" %d ", moveVal);

                    // Undo the move
                    board[i][j] = ' ';

                    // If the value of the current move is more
                    // than the best value, then update best
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                } else {
                    System.out.printf(" %c ", board[i][j]);
                }
            }
        }
        System.out.println();

        return bestMove;
    }
}
