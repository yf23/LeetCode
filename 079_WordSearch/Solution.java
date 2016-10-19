/*
    Word Search

    Given a 2D board and a word, find if the word exists in the grid.

    The word can be constructed from letters of sequentially adjacent cell,
    where "adjacent" cells are those horizontally or vertically neighboring.
    The same letter cell may not be used more than once.

    For example,
    Given board =
    [
      ['A','B','C','E'],
      ['S','F','C','S'],
      ['A','D','E','E']
    ]

    word = "ABCCED", -> returns true,
    word = "SEE", -> returns true,
    word = "ABCB", -> returns false.

    Solution:
    Use DFS (recursion) to search for next char in string.
    Modify searched route to '#' to avoid repeat search.
    Change them back after search finish.

    Yu Fu, 10/18/2016
 */

public class Solution {
    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) return true;
        if (board.length == 0 || board[0].length == 0) return false;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (helper(board, word, i, j, m, n)) return true;
            }
        }
        return false;
    }
    
    public boolean helper(char[][] board, String word, int i, int j, int m, int n) {
        if (word.length() == 0) return true;
        // If current letter matches (first char of word)
        if (board[i][j] == word.charAt(0)) {
            // If last letter in the string matches, return as exist.
            if (word.length() == 1) return true;
            // Change searched route to '#' and store it to temp var.
            char temp = board[i][j];
            board[i][j] = '#';
            // Next searching char
            String sub = word.substring(1);
            // Search for all possible neighbours
            boolean result = (i > 0 && helper(board, sub, i - 1, j, m, n)) ||
                             (i < m - 1 && helper(board, sub, i + 1, j, m, n)) ||
                             (j > 0 && helper(board, sub, i, j - 1, m, n)) ||
                             (j < n - 1 && helper(board, sub, i, j + 1, m, n));
            // Change route back to original.
            board[i][j] = temp;
            return result;
        }
        return false;
    }
}