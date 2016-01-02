/*
    Merge k Sorted Lists

    Merge k sorted linked lists and return it as one sorted list.
    Analyze and describe its complexity.

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    Yu Fu, Jan 2 2015
 */

public class Solution {

    /*
        Using Java PriorityQueue to sort all the nodes, and output as one linked list.
        Each operation of priority queue takes O(log k) time.
        Total time is O(n log k).
     */
    public ListNode mergeKListsPQ(ListNode[] lists) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (ListNode list : lists) {
            ListNode current = list;
            while (current != null) {
                pq.add(current.val);
                current = current.next;
            }
        }
        
        if (pq.peek() == null) return null;
        
        ListNode result = new ListNode(pq.poll());
        ListNode current = result;
        while (pq.peek() != null) {
            current.next = new ListNode(pq.poll());
            current = current.next;
        }
        return result;
    }

    /* Merge Sort. (log k) levels of recursion.
       For each level, merge two lists is O(n).
       Altogether should be O(n log k). */
    public ListNode mergeKListsRec(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        return mergeKListsHelper(lists, 0, lists.length - 1);
    }
    
    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        else if (start > end) return null;
        else {
            int mid = (end - start) / 2 + start;
            return mergeTwoLists(
                       mergeKListsHelper(lists, start, mid),
                       mergeKListsHelper(lists, mid + 1, end));
        }
    }
    
    private ListNode mergeTwoLists(ListNode n1, ListNode n2) {
        if (n1 == null || n2 == null) return (n1 == null) ? n2 : n1;
        
        ListNode result = new ListNode(0);
        ListNode p = result;
        
        while (n1 != null && n2 != null) {
            if (n1.val < n2.val) {
                p.next = n1;
                n1 = n1.next;
            } else {
                p.next = n2;
                n2 = n2.next;
            }
            p = p.next;
        }
        
        p.next = (n1 == null) ? n2 : n1;
        
        return result.next;
    }
}