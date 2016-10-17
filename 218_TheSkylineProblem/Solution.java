/*
    The Skyline Problem

    A city's skyline is the outer contour of the silhouette
    formed by all the buildings in that city when viewed from a distance.
    Now suppose you are given the locations and height of all the buildings
    as shown on a cityscape photo (Figure A), 
    write a program to output the skyline formed by these buildings collectively (Figure B).

    Figure A: https://leetcode.com/static/images/problemset/skyline1.jpg
    Figure B: https://leetcode.com/static/images/problemset/skyline2.jpg

    The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi],
    where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively,
    and Hi is its height.
    It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
    You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

    For instance, the dimensions of all buildings in Figure A are recorded as:
    [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 

    The output is a list of "key points" (red dots in Figure B)
    in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline.
    A key point is the left endpoint of a horizontal line segment.
    Note that the last key point, where the rightmost building ends,
    is merely used to mark the termination of the skyline, and always has zero height.
    Also, the ground in between any two adjacent buildings
    should be considered part of the skyline contour.

    For instance, the skyline in Figure B should be represented as:
    [ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

    Notes:
    The number of buildings in any input list is guaranteed to be in the range [0, 10000].
    The input list is already sorted in ascending order by the left x position Li.
    The output list must be sorted by the x position.
    There must be no consecutive horizontal lines of equal height in the output skyline.
    For instance,
    [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 
    is not acceptable;
    the three lines of height 5 should be merged into one in the final output as such
    [...[2 3], [4 5], [12 7], ...]

    Solution:
    From figure B, the key point marks the start of a new height on skyline contour.
    Define critical point to be the top left and top right end of a building.

    Sort critical point based on x coordinate.

    Maintain a priority queue to store building's height.
    At top left point (marked as start point), add the building's height to queue.
    At top right point (marked as end point), remove the building's height from queue.
    If the max height changes, the point is a key point in result.

    For the edge cases,
    If x coordinates are the same, the start point will be dealed first.
    (Avoid max height goes to zero then back up)
    If both are start point, first deal with the higher one.
    (Avoid (x, lower height), (x, higher height) both on result.)
    If both are end point, first deal with the lower one.
    (Avoid (x, higher height), (x, lower height) both on result.)

    Yu Fu, 10/04/2016
 */

// Critical Points are top left and right points of a building.
// Left point is start, right point is end.
class CriticalPoint implements Comparable<CriticalPoint> {
    public int x;
    public int y;
    public boolean isStart;
    
    public CriticalPoint(int x, int y, boolean isStart) {
        this.x = x;
        this.y = y;
        this.isStart = isStart;
    }
    
    // Sort critical points by x position in natural order.
    // If two critical points has the same x,
    // if both are start points, the one with larger height comes first.
    // If both are end points, the one with smaller height comes first.
    // Otherwise, the start point comes first.
    public int compareTo(CriticalPoint other) {
        if (other == this) return 0;
        if (other.x != this.x) return this.x - other.x;
        return ((this.isStart) ? -this.y : this.y) - ((other.isStart) ? -other.y : other.y);
    }
}

public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        ArrayList<CriticalPoint> points = new ArrayList<CriticalPoint>();
        for (int[] building : buildings) {
            // Add starting point
            points.add(new CriticalPoint(building[0], building[2], true));
            // Add ending point
            points.add(new CriticalPoint(building[1], building[2], false));
        }
        Collections.sort(points);    // Sort critical points
        
        List<int[]> result = new ArrayList<int[]>();
        // Use TreeSet to act as a max heap.
        int maxHeight = 0;
        TreeMap<Integer, Integer> pq = new TreeMap<Integer, Integer>();
        pq.put(0, 1);    // Put height 0 as base case.
        
        // For each critical point,
        // if it's start, add height to TreeMap;
        // if it's end, remove height from TreeMap.
        // If max height changes, it is a point in the result.
        for (CriticalPoint p : points) {
            // Update count of height
            if (p.isStart) {
                pq.put(p.y, pq.containsKey(p.y) ? pq.get(p.y) + 1 : 1);
            } else {
                if (pq.get(p.y) == 1) pq.remove(p.y);
                else pq.put(p.y, pq.get(p.y) - 1);
            }
            // Get new max height after change the queue.
            int newMaxHeight = pq.lastKey();
            // If max height changes, add to result.
            if (newMaxHeight != maxHeight) {
                result.add(new int[]{p.x, newMaxHeight});
                maxHeight = newMaxHeight;
            }
        }
        return result;   
    }    
}