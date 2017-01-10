/*
    Sort Characters By Frequency

    Given a string, sort it in decreasing order based on the frequency of characters.

    Example 1:

    Input:
    "tree"

    Output:
    "eert"

    Explanation:
    'e' appears twice while 'r' and 't' both appear once.
    So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
    Example 2:

    Input:
    "cccaaa"

    Output:
    "cccaaa"

    Explanation:
    Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
    Note that "cacaca" is incorrect, as the same characters must be together.
    Example 3:

    Input:
    "Aabb"

    Output:
    "bbAa"

    Explanation:
    "bbaA" is also a valid answer, but "Aabb" is incorrect.
    Note that 'A' and 'a' are treated as two different characters.

    Solution:
    Record frequency of each character, then insert them into proiority queue based on frequency.

    Yu Fu, Jan/9/2017
 */

public class Solution {
    
    class Node {
        char c;
        int freq;
        
        public Node(char c, int freq) {
            this.c = c;
            this.freq = freq; 
        }
    }
    
    public String frequencySort(String s) {
        /* Count frequency of each character */
        int[] m = new int[256];
        for (int i = 0; i < s.length(); i++) {
            m[s.charAt(i)]++;
        }

        /* Add each character to max heap, based on frequency. */
        PriorityQueue<Node> pq = new PriorityQueue<Node>(10, (Node n1, Node n2) -> (n2.freq - n1.freq));        
        for (int i = 0; i < 256; i++) {
            if (m[i] != 0) pq.add(new Node((char) i, m[i]));
        }

        /* Output each character from highest frequency. */
        StringBuilder sb = new StringBuilder();
        while (pq.size() != 0) {
            Node n = pq.poll();
            for (int i = 0; i < n.freq; i++) sb.append(n.c);
        }
        return sb.toString();
    }
}