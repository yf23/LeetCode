/*
    Peeking Iterator

    Given an Iterator class interface with methods: next() and hasNext(),
    design and implement a PeekingIterator that support the peek() operation
    -- it essentially peek() at the element that will be returned by the next call to next().

    Here is an example.
    Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

    Call next() gets you 1, the first element in the list.

    Now you call peek() and it returns 2, the next element.
    Calling next() after that still return 2.

    You call next() the final time and it returns 3, the last element.
    Calling hasNext() after that should return false.

    Hint:
    Think of "looking ahead". You want to cache the next element.
    Is one variable sufficient? Why or why not?
    Test your design with call order of peek() before next() vs next() before peek().
    For a clean implementation, check out Google's guava library source code.

    Solution:
    When peeking, store the number in a class variable if it has not been peeked.

    Yu Fu, 10/10/2016
 */

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iter;
    boolean hasPeeked;
    Integer peekedNum;
    
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iter = iterator;
        // Get first number from iterator.
        this.peek();
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        // If has not peeked, get the first number from iterator and store it.
        // If no next number, store null.
        if (!hasPeeked) {
            hasPeeked = true;
            if (iter.hasNext()) peekedNum = iter.next();
            else peekedNum = null;
        }
        return peekedNum;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        // Get current stored number.
        Integer result = this.peek();
        // Peek next number and store in local variable.
        hasPeeked = false;
        this.peek();
        return result;
    }

    @Override
    public boolean hasNext() {
        // If stored variable is null, no next number.
        return peekedNum != null;
    }
}
