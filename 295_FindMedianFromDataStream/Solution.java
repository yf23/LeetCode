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

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();