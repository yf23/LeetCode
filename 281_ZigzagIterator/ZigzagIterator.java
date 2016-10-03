/*
    Zigzag Iterator

    Given two 1d vectors, implement an iterator to return their elements alternately.

    For example, given two 1d vectors:

    v1 = [1, 2]
    v2 = [3, 4, 5, 6]
    By calling next repeatedly until hasNext returns false,
    the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

    Yu Fu, 10/01/2016
 */

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */

public class ZigzagIterator {    
    boolean onV1;       // If the value will be retrieved from v1.
    int i; int j;       // Index of next value on each list
    List<Integer> v1;   // List v1
    List<Integer> v2;   // List v2
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        onV1 = true;
        i = 0;
        j = 0;
        this.v1 = new ArrayList<Integer>(v1);
        this.v2 = new ArrayList<Integer>(v2);
    }

    public int next() {
        // If one of the index is out of range,
        // retrieve next value from the other list.
        if (i >= v1.size()) return v2.get(j++);
        if (j >= v2.size()) return v1.get(i++);
        
        // Switch onV1 before return.
        onV1 = !onV1;
        // Since we switched, onV1 is true means return value from v2, otherwise from v1.
        return onV1 ? v2.get(j++) : v1.get(i++);
    }

    // If both index are out of range, return false.
    public boolean hasNext() {
        return i < v1.size() || j < v2.size();
    }
}