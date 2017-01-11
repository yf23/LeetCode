/*
    Add Two Numbers II

    You are given two non-empty linked lists representing two non-negative integers.
    The most significant digit comes first and each of their nodes contain a single digit.
    Add the two numbers and return it as a linked list.

    You may assume the two numbers do not contain any leading zero, except the number 0 itself.

    Follow up:
    What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

    Example:

    Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 8 -> 0 -> 7

    Solution:
    Put two linkedlists into stacks

    Yu Fu, Jan/10/2017
 */

 /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        ListNode cur = l1;
        while (cur != null) {
            s1.push(cur.val);
            cur = cur.next;
        }
        
        Stack<Integer> s2 = new Stack<Integer>();
        cur = l2;
        while (cur != null) {
            s2.push(cur.val);
            cur = cur.next;
        }

        int carry = 0;
        Stack<Integer> sr = new Stack<Integer>();
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = (s1.isEmpty() ? 0 : s1.pop()) + (s2.isEmpty() ? 0 : s2.pop()) + carry;
            carry = sum / 10;
            sum %= 10;
            sr.push(sum);
        }
        if (carry > 0) sr.push(carry);

        ListNode result = new ListNode(0);
        cur = result;
        while (!sr.isEmpty()) {
            cur.next = new ListNode(sr.pop());
            cur = cur.next;
        }
        return result.next;
    }
}