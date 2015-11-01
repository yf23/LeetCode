'''
Spiral Matrix

Given a matrix of m x n elements (m rows, n columns),
return all elements of the matrix in spiral order.

For example,
Given the following matrix:

[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

You should return [1,2,3,6,9,8,7,4,5].

Turn the matrix each loop and add first line to the output list.

Yu Fu, Oct 31 2015
'''

class Solution(object):
    def spiralOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        list = []
        while len(matrix) != 0:
            list += matrix[0]
            matrix = self.turn(matrix)
        return list
    
    '''
    Remove the first line of matrix, 
    then turn the given matrix counter-clockwise.
    
    Example:
    
    Input
    [[ 1, 2, 3 ],
     [ 4, 5, 6 ],
     [ 7, 8, 9 ]]
     
    Output
    [[6, 9],
     [5, 8],
     [4, 7]]
    '''
    def turn(self, matrix):
        del matrix[0]
        if len(matrix) == 0:
            return matrix
        new_matrix = []
        for x in reversed(range(len(matrix[0]))):
            new_row = []
            for row in matrix:
                new_row.append(row[x])
            new_matrix.append(new_row)
        return new_matrix
            