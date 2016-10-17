/*
    Flatten Nested List Iterator

    Given a nested list of integers, implement an iterator to flatten it.

    Each element is either an integer, or a list
    -- whose elements may also be integers or other lists.

    Example 1:
    Given the list [[1,1],2,[1,1]],

    By calling next repeatedly until hasNext returns false,
    the order of elements returned by next should be: [1,1,2,1,1].

    Example 2:
    Given the list [1,[4,[6]]],

    By calling next repeatedly until hasNext returns false,
    the order of elements returned by next should be: [1,4,6].

    Solution:
    When meeting a list, recursively build an iterator.

    Yu Fu, 10/16/2016
 */

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

public class NestedIterator implements Iterator<Integer> {   
    ArrayList<Integer> l;
    int cur;
        
    public NestedIterator(List<NestedInteger> nestedList) {
        l = new ArrayList<Integer>();
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) l.add(n.getInteger());
            else {
                NestedIterator nIter = new NestedIterator(n.getList());
                while (nIter.hasNext()) l.add(nIter.next());
            }
        }
        cur = 0;
    }

    @Override
    public Integer next() {
        return l.get(cur++);
    }

    @Override
    public boolean hasNext() {
        return cur < l.size();
    }
}