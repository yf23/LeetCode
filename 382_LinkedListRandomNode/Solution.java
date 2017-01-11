/*
    Linked List Random Node

    Given a singly linked list, return a random node's value from the linked list.
    Each node must have the same probability of being chosen.

    Follow up:
    What if the linked list is extremely large and its length is unknown to you?
    Could you solve this efficiently without using extra space?

    Example:

    // Init a singly linked list [1,2,3].
    ListNode head = new ListNode(1);
    head.next = new ListNode(2);
    head.next.next = new ListNode(3);
    Solution solution = new Solution(head);

    // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
    solution.getRandom();

    Solution:
    For the i-th new number, replace result with probablity 1 / (i + 1).
    The probability for i-th number to be result is:
    1 / (i + 1) * (i + 1) / (i + 2) * ... * (n - 1) / n = 1 / n

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
    ListNode head;
    int result;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        this.head = head;
        this.result = 0;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random r = new Random();
        result = head.val;
        
        ListNode cur = head.next;
        int count = 2;
        while (cur != null) {
            if (r.nextInt(count) == count - 1) result = cur.val;
            cur = cur.next;
            count++;
        }
        return result;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */