/*
    Find Median from Data Stream

    Median is the middle value in an ordered integer list.
    If the size of the list is even, there is no middle value.
    So the median is the mean of the two middle value.

    Examples: 
    [2,3,4] , the median is 3

    [2,3], the median is (2 + 3) / 2 = 2.5

    Design a data structure that supports the following two operations:

    void addNum(int num) - Add a integer number from the data stream to the data structure.
    double findMedian() - Return the median of all elements so far.
    
    For example:
    add(1)
    add(2)
    findMedian() -> 1.5
    add(3) 
    findMedian() -> 2

    Solution:
    Keep a max heap for smaller half, and a min heap for larger half.
    When a new number comes in, add into larger half.
    Then from the larger half, pop its min element and add to smaller half.
    If the smaller half has more than 2 elements then the larger half,
    pop max from smaller half to larger half until smaller half is only 1 element more.

    When return median, if two heaps has the same size, the median is average of two peaks.
    If small peak is larger (and it cannot be smaller based on alrogirthms when adding),
    the median the peak of small heap.
 */

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();
public class MedianFinder {
    PriorityQueue<Integer> large = new PriorityQueue<Integer>(10, (x, y) -> (x - y));
    PriorityQueue<Integer> small = new PriorityQueue<Integer>(10, (x, y) -> (y - x));
    // Adds a number into the data structure.
    public void addNum(int num) {
        large.add(num);
        small.add(large.poll());
        if (small.size() - large.size() > 1) {
            large.add(small.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        if (small.size() > large.size()) return small.peek();
        else return (small.peek() + large.peek()) / 2.0;
    }
};
