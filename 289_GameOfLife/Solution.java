/*
    Game of Life

    According to the Wikipedia's article: "The Game of Life, also known simply as Life,
    is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

    Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
    Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
    using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

    Write a function to compute the next state (after one update) of the board given its current state.

    Follow up: 
    1. Could you solve it in-place?
       Remember that the board needs to be updated at the same time:
       You cannot update some cells first and then use their updated values to update other cells.
    2. In this question, we represent the board using a 2D array.
       In principle, the board is infinite,
       which would cause problems when the active area encroaches the border of the array.
       How would you address these problems?

    Solution:
    In place solution:
    Each cell use two digits binary number to represent current status and next status.
    Lower digit is current state, higher digit is next state.
    After calculate all the states for next digits, shift right will filling zero.

    00   -   0   -   Now dead, next dead
    01   -   1   -   Now living, next dead
    10   -   2   -   Now dead, next living
    11   -   3   -   Now living, next living

    To get current status, mod 2.
    To update next status to living, add 2.
    To update next status to dead, remain current value.

    Yu Fu, 10/01/2016
 */

public class Solution {
    public void gameOfLife(int[][] board) {
        if (board.length == 0 || board[0].length == 0) return;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Count number of living neighbours.
                int count = countLiveNeighbours(board, i, j, m, n);
                // If current is living
                if (board[i][j] % 2 == 1) {
                    // Next is living only with 2 or 3 living neighbours.
                    if (count == 2 || count == 3) board[i][j] += 2; 
                // If current is dead
                } else {
                    // Next is living only with 3 living neighbours.
                    if (count == 3) board[i][j] = 2;
                }
            }
        }
        // All shift right once (fill with 0) to get next state.
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>>= 1;
            }
        }
    }
    
    // Count number of currently living neighbours.
    private int countLiveNeighbours(int[][] board, int i, int j, int m, int n) {
        int sum = 0;
        boolean atLeft = (j == 0);
        boolean atRight = (j == n - 1);
        boolean atTop = (i == 0);
        boolean atBottom = (i == m - 1);
        if (!atLeft) sum += (board[i][j - 1] % 2);
        if (!atRight) sum += (board[i][j + 1] % 2);
        if (!atTop) sum += (board[i - 1][j] % 2);
        if (!atBottom) sum += (board[i + 1][j] % 2);
        if (!atLeft && !atTop) sum += (board[i - 1][j - 1] % 2);
        if (!atLeft && !atBottom) sum += (board[i + 1][j - 1] % 2);
        if (!atRight && !atTop) sum += (board[i - 1][j + 1] % 2);
        if (!atRight && !atBottom) sum += (board[i + 1][j + 1] % 2);
        return sum;
    }
}
