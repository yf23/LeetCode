/*
    Battleships in a Board

    Given an 2D board, count how many different battleships are in it.
    The battleships are represented with 'X's, empty slots are represented with '.'s.
    You may assume the following rules:

    You receive a valid board, made of only battleships or empty slots.
    Battleships can only be placed horizontally or vertically.
    In other words, they can only be made of the shape 1xN (1 row, N columns)
    or Nx1 (N rows, 1 column), where N can be of any size.
    
    At least one horizontal or vertical cell separates between two battleships - 
    there are no adjacent battleships.

    Example:
    X..X
    ...X
    ...X
    In the above board there are 2 battleships.

    Invalid Example:
    ...X
    XXXX
    ...X
    This is an invalid board that you will not receive - 
    as battleships will always have a cell separating between them.

    Follow up:
    Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?

    Solution:
    For each battle ship, only check the starting grid, which is either the upmost or the leftmost.
    Therefore there should be empty on left and up grid.

    Yu Fu, Jan/9/2017
 */

public class Solution {
    public int countBattleships(char[][] board) {
        int count = 0;
        int h = board.length;
        int w = board[0].length;
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean upEmpty = i - 1 < 0 || board[i - 1][j] == '.';
                boolean leftEmpty = j - 1 < 0 || board[i][j - 1] == '.';
                if (board[i][j] == 'X' && upEmpty && leftEmpty) count++;
            }
        }
        
        return count;       
    }
}