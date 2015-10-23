/*
    Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
    The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
    
    The solution is based from the answer by youke, rewrite to Java:
    https://leetcode.com/discuss/38474/70ms-concise-python-solution

    Mapping each integer i in [1, 9] to the i-th prime number.
    For each row/column/block, assign a number starting with the value 1.
    If the number stored mod i-th prime number is zero,
    it means the number has appeared in that row/column/block twice, so the sudoku is invalid.
    If not zero, multiply the i-th prime number with the assigned number.

    Yu Fu, Oct 23 2015
*/

import java.util.Arrays;

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Create an mapping array of prime numbers
        int[] PRIMES = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23};
        
        // Initialize value for each row/column/block
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] blocks = new int[9];
        Arrays.fill(rows, 1);
        Arrays.fill(cols, 1);
        Arrays.fill(blocks, 1);
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Skip the empty space
                if (board[i][j]=='.') {
                    continue;
                }
                // Test if the number has appeared by test if the mod is 0.
                // The mod 0 means the number has appeared before in row/col/block.
                int p = PRIMES[(int) (board[i][j] - '0')];
                if (rows[i] % p == 0 || cols[j] % p == 0 || blocks[i/3*3 + j/3] % p == 0) {
                    return false;
                } else {
                    // Update values
                    rows[i] *= p;
                    cols[j] *= p;
                    blocks[i/3*3 + j/3] *= p;
                }
            }
        } 
        return true;
    }
}