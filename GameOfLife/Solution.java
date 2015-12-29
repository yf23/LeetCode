/*
    Game of Life

    According to the Wikipedia's article:
    "The Game of Life, also known simply as Life,
     is a cellular automaton devised by the British
     mathematician John Horton Conway in 1970."

    Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
    Each cell interacts with its eight neighbors (horizontal, vertical, diagonal)
    using the following four rules (taken from the above Wikipedia article):

    Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by over-population..
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    
    Write a function to compute the next state (after one update) of the board given its current state.

    Yu Fu, Dec 29 2015
 */
    
public class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = (m == 0) ? 0 : board[0].length;
        int[][] newBoard = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = countLiveNeighbour(i, j, m, n, board);
                boolean live = (board[i][j] == 1);
                if ((live && (count < 2 || count > 3)) || (!live && count != 3)) {
                    newBoard[i][j] = 0;
                } else {
                    newBoard[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }
    
    private int countLiveNeighbour(int x, int y, int m, int n, int[][] board) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (x + i >= 0 && x + i < m && y + j >= 0 && y + j < n && !(i == 0 && j == 0)) {
                    count += board[x + i][y + j];
                }
            }
        }        
        return count;
    }
}