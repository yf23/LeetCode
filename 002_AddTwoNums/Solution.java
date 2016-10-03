/*
    Add Two Numbers

    You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

    Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8

    Yu Fu, 09/28/2016
 */

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode result = new ListNode(0);   // Pesuedohead makes life much easier.
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode cur3 = result;
        
        // If both array has digits.
        while (cur1 != null && cur2 != null) {
            int sum = cur1.val + cur2.val + carry;
            carry = sum / 10;
            sum %= 10;
            cur3.next = new ListNode(sum);
            cur1 = cur1.next;
            cur2 = cur2.next;
            cur3 = cur3.next;
        }
        
        // When one of the array has digits.
        while (cur1 != null) {
            int sum = cur1.val + carry;
            carry = sum / 10;
            sum %= 10;
            cur3.next = new ListNode(sum);
            cur1 = cur1.next;
            cur3 = cur3.next;
        }
        
        while (cur2 != null) {
            int sum = cur2.val + carry;
            carry = sum / 10;
            sum %= 10;
            cur3.next = new ListNode(sum);
            cur2 = cur2.next;
            cur3 = cur3.next;
        }
        
        // Add carry to the end.
        while (carry != 0) {
            cur3.next = new ListNode(carry % 10);
            carry /= 10;
            cur3 = cur3.next;
        }
        
        return result.next;
    }
}