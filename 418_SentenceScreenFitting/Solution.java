/*
    Sentence Screen Fitting

    Given a rows x cols screen and a sentence represented by a list of words,
    find how many times the given sentence can be fitted on the screen.

    Note:
    A word cannot be split into two lines.
    The order of words in the sentence must remain unchanged.
    Two consecutive words in a line must be separated by a single space.
    Total words in the sentence won't exceed 100.
    Length of each word won't exceed 10.
    1 ≤ rows, cols ≤ 20,000.

    Example 1:
    Input:
    rows = 2, cols = 8, sentence = ["hello", "world"]

    Output: 
    1

    Explanation:
    hello---
    world---
    The character '-' signifies an empty space on the screen.

    Example 2:
    Input:
    rows = 3, cols = 6, sentence = ["a", "bcd", "e"]

    Output: 
    2

    Explanation:
    a-bcd- 
    e-a---
    bcd-e-
    The character '-' signifies an empty space on the screen.

    Example 3:
    Input:
    rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]

    Output: 
    1

    Explanation:
    I-had
    apple
    pie-I
    had--
    The character '-' signifies an empty space on the screen.

    Solution:
    First, connect all the words with space. Also leave a space at end.
    Example: ["hello", "word"] => "hello world "

    Then, move index pointer by the number of rows.
    Cases:
    1. If the pointer hits first letter of a word, it is the first word of next line.
    2. If the pointer hits a space, the next word is the first word of next line.
    3. If the pointer hits the middle of word, this word will not fit in current line.
       This word will be the first word of next line.
    
    Move pointer to the first letter of first word of next line.
    For the 3rd case, move pointer forward until the previous char of pointer is space.

    Repeat addition for the number of rows times.
    The pointer will always stops at the first letter of next word which does not fit.

    Yu Fu, 11/28/2016
 */
public class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        // Create a whole sentence of words connected by spaces
        StringBuilder sb = new StringBuilder();
        for (String s : sentence) {
            if (s.length() > cols) return 0;
            sb.append(s);
            sb.append(' ');
        }
        String whole = sb.toString();

        // Start addition to index pointer
        int ind = 0;
        for (int i = 0; i < rows; i++) {
            ind += cols;
            // If hits a space, goes to the next letter.
            if (whole.charAt(ind % whole.length()) == ' ') ind++;
            // If hits in the middle of a word (previous char is not space),
            // find backward until reaches the first letter of the word.
            while (whole.charAt((ind - 1) % whole.length()) != ' ') ind--;
        }
        
        // ind always stops at the first letter of next word which does not fit
        return ind / whole.length();        
    }
}