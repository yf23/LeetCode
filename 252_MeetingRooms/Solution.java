/*
    Meeting Rooms

    Given an array of meeting time intervals consisting of start and end times
    [[s1,e1],[s2,e2],...] (si < ei),
    determine if a person could attend all meetings.

    For example,
    Given [[0, 30],[5, 10],[15, 20]],
    return false.
    
    Solution:
    Sort the array based on start time.
    If previous interval's end time is later than current interval's start time,
    the man cannot attend both meetings.

    Yu Fu, 09/29/2016
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
    public boolean canAttendMeetings(Interval[] intervals) {
        // Sort the array based on start time.
        Arrays.sort(intervals, (x, y) -> x.start - y.start);
        // Find if there is start time earlier than previous's end time.
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i-1].end > intervals[i].start) return false;
        }
        return true;
    }
}
