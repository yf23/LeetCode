/*
    Merge Two Sorted Lists

    Merge two sorted linked lists and return it as a new list.
    The new list should be made by splicing together the nodes of the first two lists.

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
    public ListNode mergeTwoLists(ListNode n1, ListNode n2) {
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
