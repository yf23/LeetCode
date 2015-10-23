'''
    Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
    The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

    Use list.count() to count the appearance of each number in row/column/block.
    Not good at performance (148 ms), might because of the list.count() function.

    Find a nice solution here by youke, only ~70 ms:
    https://leetcode.com/discuss/38474/70ms-concise-python-solution
    Mapping each integer i in [1, 9] to the i-th prime number.
    For each row/column/block, assign a number starting with the value 1.
    If the number stored mod i-th prime number is zero,
    it means the number has appeared in that row/column/block twice, so the sudoku is invalid.
    If not zero, multiply the i-th prime number with the assigned number.

    The algorithm above is rewrite to java version in Solution.java.

    Yu Fu, Oct 23 2015
'''

class Solution(object):
    def isValidSudoku(self, board):
        """
        :type board: List[List[str]]
        :rtype: bool
        """
        if len(board) != 9: return False
        
        # Test each row for repeat numbers
        for row in board:
            for i in range(9):
                if row.count(str(i+1)) > 1:
                    return False
        
        # Test each column for repeat numbers
        for colCount in range(9):
            col = []
            for row in board:
                col.append(row[colCount])
            for i in range(9):
                if col.count(str(i+1)) > 1:
                    return False
        
        # Test each block for repeat numbers
        for blockCount in range(9):
            block = []
            for row in range(3):
                for col in range(3):
                    block.append(board[blockCount // 3 * 3 + row][blockCount % 3 * 3 + col])
            for i in range(9):
                if block.count(str(i+1)) > 1:
                    return False
        
        return True
