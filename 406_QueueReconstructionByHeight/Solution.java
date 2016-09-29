/*
    Queue Reconstruction by Height

    Suppose you have a random list of people standing in a queue.
    Each person is described by a pair of integers (h, k),
    where h is the height of the person,
    and k is the number of people in front of this person who have a height greater than or equal to h.
    Write an algorithm to reconstruct the queue.

    Note:
    The number of people is less than 1,100.

    Example:
    Input:
    [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

    Output:
    [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

    Solution:
    First, sort the pairs from higher to lower height.
    For pairs with the same height, sort from lower to higher k.
    Insert sorted pairs into list.
    k is the index to insert. Existing pairs are shifted to right at insertion.

    Example:
    [7, 0]
    [[7, 0]]

    [7, 1]
    [[7, 0], [7, 1]]

    [6, 1]
    [[7, 0], [6, 1], [7, 1]]

    [5, 0]
    [[5, 0], [7, 0], [6, 1], [7, 1]]

    [5, 2]
    [[5, 0], [7, 0], [5, 2], [6, 1], [7, 1]]

    [4, 4]
    [[5, 0], [7, 0], [5, 2], [6, 1], [4, 4], [7, 1]]

    Yu Fu, 09/28/2016
 */
    
public class Solution {
    // Class pair that stores h and k in pair.
    class Pair implements Comparable<Pair> {
        public int h;
        public int k;
        
        public Pair(int h, int k) {
            this.h = h;
            this.k = k;
        }
        
        // Higher people are considered smaller when comparing.
        // For people with same height, the one with less people in front of him (k) is samller.
        public int compareTo(Pair other) {
            if (this == other) return 0;
            if (other.h != this.h) return other.h - this.h;
            else return this.k - other.k;
        }
    }
    
    // Sort all pairs according to the order defined above.
    // Add pairs in sorted order into list.
    // k is the index to insert.
    public int[][] reconstructQueue(int[][] people) {
        TreeSet<Pair> s = new TreeSet<Pair>();
        for (int i = 0; i < people.length; i++) {
            s.add(new Pair(people[i][0], people[i][1]));
        }
        
        ArrayList<Pair> l = new ArrayList<Pair>();
        for (Pair p : s) {
            l.add(p.k, p);
        }
        
        int[][] result = new int[people.length][2];
        for (int i = 0; i < l.size(); i++) {
            result[i][0] = l.get(i).h;
            result[i][1] = l.get(i).k;
        }
        return result;
    }
}