/*
    Android Unlock Patterns

    Given an Android 3x3 key lock screen and two integers m and n,
    where 1 ≤ m ≤ n ≤ 9,
    count the total number of unlock patterns of the Android lock screen,
    which consist of minimum of m keys and maximum n keys.

    Rules for a valid pattern:
    Each pattern must connect at least m keys and at most n keys.
    All the keys must be distinct.
    If the line connecting two consecutive keys in the pattern passes through any other keys,
    the other keys must have previously selected in the pattern.
    No jumps through non selected key is allowed.
    The order of keys used matters.

    Explanation:
    | 1 | 2 | 3 |
    | 4 | 5 | 6 |
    | 7 | 8 | 9 |

    Invalid move: 4 - 1 - 3 - 6 
    Line 1 - 3 passes through key 2 which had not been selected in the pattern.

    Invalid move: 4 - 1 - 9 - 2
    Line 1 - 9 passes through key 5 which had not been selected in the pattern.

    Valid move: 2 - 4 - 1 - 3 - 6
    Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

    Valid move: 6 - 5 - 4 - 1 - 9 - 2
    Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.

    Example:
    Given m = 1, n = 1, return 9.
    
    Solution:
    Use backtracking (DFS) to serach for possible next move.
    Keep a skip 2D array to check the skipping number between two numbers.
    skip[i][j] == 0 means i and j are neighbours.

    Also, [1, 3, 7, 9], [2, 4, 6, 8] are symmetric,
    so caculate the number of patterns starting from a number from each group is enough.

    Yu Fu, 12/02/2016
 */

public class Solution {
    public int numberOfPatterns(int m, int n) {
        // Mark if the number is selected.
        boolean[] isSelected = new boolean[10];

        // Create skip array.
        int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[3][9] = skip[9][3] = 6;
        skip[9][7] = skip[7][9] = 8;
        skip[7][1] = skip[1][7] = 4;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = 
        skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        
        // From result keys count from m to n, run DFS.
        // [1, 3, 5, 7] and [2, 4, 6, 8] are symmetric, so times 4.
        int count = 0;
        for (int i = m; i <= n; i++) {
            count += DFS(1, 1, i, isSelected, skip) * 4;
            count += DFS(2, 1, i, isSelected, skip) * 4;
            count += DFS(5, 1, i, isSelected, skip);
        }

        return count;
    }
    
    private int DFS(int curNum, int curCount, int finishCount, boolean[] isSelected, int[][] skip) {
        // If number of current keys meets the number of result keys, return 1 as it's only solution.
        if (curCount == finishCount) return 1;

        isSelected[curNum] = true;  // Select current number       
        // For each possible next number,
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            // If the number has not selected,
            // and the number is neighbour (skip == 0) or the skip number between them is selected,
            // it can be the next number.
            if (!isSelected[i] && (skip[curNum][i] == 0 || isSelected[skip[curNum][i]])) {
                count += DFS(i, curCount + 1, finishCount, isSelected, skip);
            }
        }
        isSelected[curNum] = false;  // Deselect current number for backtracking.
        return count;
    }
}

