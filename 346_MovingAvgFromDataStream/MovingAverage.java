/*
    Moving Average from Data Stream

    Given a stream of integers and a window size,
    calculate the moving average of all integers in the sliding window.

    For example,
    MovingAverage m = new MovingAverage(3);
    m.next(1) = 1
    m.next(10) = (1 + 10) / 2
    m.next(3) = (1 + 10 + 3) / 3
    m.next(5) = (10 + 3 + 5) / 3

    Solution:
    Keep a queue with max given size.
    Dequeue old values when new values arrive.

    Yu Fu, 09/29/2016
 */

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */

public class MovingAverage {   
    int size;
    int sum;
    Queue<Integer> q;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.sum = 0;
        this.size = size;
        this.q = new LinkedList<Integer>();
    }
    
    public double next(int val) {
        q.add(val);
        sum += val;
        if (q.size() - 1 < size) {
            // Queue size is less than max size.
            return (double) sum / q.size();
        } else {
            // Queue size is at maximum. Dequeue oldest value.
            sum -= q.poll();
            return (double) sum / size;
        }
    }
}

