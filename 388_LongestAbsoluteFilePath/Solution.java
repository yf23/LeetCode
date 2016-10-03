/*
    Longest Absolute File Path

    Suppose we abstract our file system by a string in the following manner:

    The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

    dir
        subdir1
        subdir2
            file.ext
    The directory dir contains an empty sub-directory subdir1
    and a sub-directory subdir2 containing a file file.ext.

    The string
    "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
    represents:

    dir
        subdir1
            file1.ext
            subsubdir1
        subdir2
            subsubdir2
                file2.ext

    The directory dir contains two sub-directories subdir1 and subdir2.
    subdir1 contains a file file1.ext and an empty second-level sub-directory subsubdir1.
    subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

    We are interested in finding the longest (number of characters) absolute path to a file within our file system.
    For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext",
    and its length is 32 (not including the double quotes).

    Given a string representing the file system in the above format,
    return the length of the longest absolute path to file in the abstracted file system.
    If there is no file in the system, return 0.

    Note:
    The name of a file contains at least a . and an extension.
    The name of a directory or sub-directory will not contain a .
    Time complexity required: O(n) where n is the size of the input string.

    Notice that a/aa/aaa/file1.txt is not the longest file path,
    if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.

    Solution:
    Use a stack to update length of path for each level.
    For example: a/aa/aaa/file1.txt will be:

    file1.txt     =>     7
    aaa/                 4
    aa/                  3
    a/                   2

    Use an array and a pointer to simulate stack behavior.

    When a new line comes, calculate its level.
    Pop all values in the stack with same or higher levels.
    Push new line length to stack.

    If new line is filename, update result with sum of stack.

    Yu Fu, 09/30/2016
 */

public class Solution {
    public int lengthLongestPath(String input) {
        int maxLength = 0;    // Keep record of max file path length.
        int curLength = 0;

        String[] lines = input.split("\\n");       // Split input to lines.
        int[] record = new int[lines.length];      // Max stack size is number of lines

        int prevLevel = 0;                         // Level of previous line (also index of top of stack)
        for (String line : lines) {
            int lineLength = line.length();        // Get length of line
            if (!line.contains(".")) lineLength++; // If dir, add 1 to length (file sepeartor)
            int curLevel = line.lastIndexOf("\t") + 1; // Find the level of current line (number of tabs)
            lineLength -= curLevel;                // Remove length of tabs
            
            // Act as a stack.
            // While prevLevel greater or equal than current level,
            // pop and reduce popped value from current path length.
            while (prevLevel >= curLevel) {
                curLength -= record[prevLevel--];
            }
            
            // Push current line length to current level, and add it to current length.
            record[++prevLevel] = lineLength;
            curLength += lineLength;
            
            // If current line is filename (contains '.'), update max path length.
            if (line.contains(".")) {
                maxLength = Math.max(maxLength, curLength);
            }
        }
        return maxLength;
    }
}