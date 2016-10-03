/*
    Shortest Word Distance

    Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

    For example,
    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

    Given word1 = “coding”, word2 = “practice”, return 3.
    Given word1 = "makes", word2 = "coding", return 1.

    Note:
    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

    Yu Fu, 09/29/2016
 */

public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            // When find an occurance of word1,
            // search both left and right side for word2.
            // Maintain a global min for distance.
            if (words[i].equals(word1)) {
                // Search left side for closest word2.
                for (int j = i - 1; j >= 0; j--) {
                    if (words[j].equals(word2)) {
                        minDis = Math.min(i - j, minDis);
                        break;
                    }
                }
                // Search right side for closest word2.
                for (int j = i + 1; j < words.length; j++) {
                    if (words[j].equals(word2)) {
                        minDis = Math.min(j - i, minDis);
                        break;
                    }
                }
            }
        }
        return minDis;
    }
}