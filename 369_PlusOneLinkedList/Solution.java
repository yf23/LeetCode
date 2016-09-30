/*
    Plus One Linked List

    Given a non-negative number represented as a singly linked list of digits, plus one to the number.

    The digits are stored such that the most significant digit is at the head of the list.

    Example:
    Input:
    1->2->3

    Output:
    1->2->4

    Yu Fu, 09/29/2016
 */

public class Solution {
    public ListNode plusOne(ListNode head) {
        head = reverse(head);   // Reverse the list

        // Add from the head.
        int carry = 1;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            cur.val += carry;
            carry = cur.val / 10;
            cur.val %= 10;
            cur = cur.next;
        }
        
        // If add to last digit still have carry,
        // add carry to the end of list as the most significant digit.
        cur.val = cur.val + carry;
        if (cur.val >= 10) {
            cur.next = new ListNode(cur.val / 10);
            cur.val %= 10;
        }
        
        // Reverse the list again.
        return reverse(head);
    }
    
    // Reverse given linked list.
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode i = newHead.next;
        ListNode j = i.next;
        ListNode k = null;
        while (j != null) {
            k = j.next;
            j.next = newHead.next;
            newHead.next = j;
            i.next = k;
            j = i.next;
        }
        return newHead.next;
    }
}
