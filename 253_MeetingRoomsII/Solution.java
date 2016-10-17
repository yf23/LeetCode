/*
    Meeting Rooms II

    Given an array of meeting time intervals consisting of start and end times
    [[s1,e1],[s2,e2],...] (si < ei),
    find the minimum number of conference rooms required.

    For example,
    Given [[0, 30],[5, 10],[15, 20]],
    return 2.

    Yu Fu, 10/16/2016
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
        // Sort the intervals on start time.
        Arrays.sort(intervals, (x, y) -> (x.start - y.start));
        // All the rooms. Keys are the end time of the room; values are number of rooms has that end time.
        TreeMap<Integer, Integer> rooms = new TreeMap<Integer, Integer>();
        for (int i = 0; i < intervals.length; i++) {
            // Find an available room, which has a end time not greater than current interval's start time.
            Integer available = rooms.containsKey(intervals[i].start) ?
                                intervals[i].start : rooms.lowerKey(intervals[i].start);
            // Find such room, remove the room.
            if (available != null) {
                if (rooms.get(available) == 1) rooms.remove(available);
                else rooms.put(available, rooms.get(available) - 1);
            }
            // Add new room with end time as current interval's end time.
            rooms.put(intervals[i].end, rooms.containsKey(intervals[i].end) ? rooms.get(intervals[i].end) + 1 : 1);
        }
        // Return the number of rooms.
        int sum = 0;
        for (int n : rooms.values()) sum += n;
        return sum;
    }
}