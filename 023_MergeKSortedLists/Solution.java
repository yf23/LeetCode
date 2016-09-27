/*	
	Merge k Sorted Lists

    Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

    Solution:
    Use divide and conquer method. Merge 2 sorted list one time, and merge all the results.

    Complexity:
    If there are k lists with length n,
    the algorithm is O(n * k * log k).
	
    Yu Fu, 09/27/2016
 */

public class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return partition(lists, 0, lists.length - 1);
    }
    
    // Partition multiple linked lists to half and merge two halves.
    // If only one linked list is left, return itself.
    public ListNode partition(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        if (start < end) {
            ListNode l1 = partition(lists, start, (start + end) / 2);
            ListNode l2 = partition(lists, (start + end) / 2 + 1, end);
            return merge(l1, l2);
        } else return null;
    }
    
    // Recursively merge two sorted linked lists.
    public ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = merge(l1.next, l2);
            return l1;
        } else {
            l2.next = merge(l1, l2.next);
            return l2;
        }
    }
}
    