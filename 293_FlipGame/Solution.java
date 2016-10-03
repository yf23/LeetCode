/*
    Flip Game
    
    You are playing the following Flip Game with your friend:
    Given a string that contains only these two characters: + and -,
    you and your friend take turns to flip two consecutive "++" into "--".
    The game ends when a person can no longer make a move and therefore the other person will be the winner.

    Write a function to compute all possible states of the string after one valid move.

    For example, given s = "++++", after one move, it may become one of the following states:

    [
      "--++",
      "+--+",
      "++--"
    ]

    If there is no valid move, return an empty list [].

    Yu Fu, 09/29/2016
 */

public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<String>();
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < s.length() - 1; i++) {
            // If two consecutive "++", change them to "--" in StringBuffer and append to result list.
            // Change them back to "++" in StrinBuffer for following steps.
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                sb.setCharAt(i, '-');
                sb.setCharAt(i + 1, '-');
                result.add(sb.toString());
                sb.setCharAt(i, '+');
                sb.setCharAt(i + 1, '+');
            }
        }
        return result;
    }
}