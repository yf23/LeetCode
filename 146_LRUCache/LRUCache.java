/* 
    LRU Cache

    Design and implement a data structure for Least Recently Used (LRU) cache.
    It should support the following operations: get and set.

    get(key) - Get the value (will always be positive) of the key,
               if the key exists in the cache, otherwise return -1.
    set(key, value) - Set or insert the value if the key is not already present.
                      When the cache reached its capacity,
                      it should invalidate the least recently used item before inserting a new item.

    Solution:
    Use a combination of HashMap (for quick retrieve) and double linked list (for quick update).
    A double linked list node contains key and value.

    When a new pair is put in map, create a new node.
    If the HashMap size equals to capacity, pop the tail node as well as remove the pair in HashMap.
    The new node goes into the head of list, and add the new pair into HashMap.
    
    When a key is requeseted by get(), get the node from HashMap.
    Remove the node from double linked list, and add the node to the head of list.

    Therefore, the head of list is always most recently used,
    and the tail of list is least recently used.

    We implement remove() and addToHead() methods for double linked list.

    Yu Fu, 09/30/2016
 */

public class LRUCache {
    
    // Def of doublie linked list node.
    // Stores key and value.
    class DoubleListNode {
        int key;
        int val;
        DoubleListNode prev;
        DoubleListNode next;
        
        public DoubleListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    HashMap<Integer, DoubleListNode> mp;
    DoubleListNode head;
    DoubleListNode tail;
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        mp = new HashMap<Integer, DoubleListNode>(capacity);
    }
    
    // Get the node from HashMap. If not exist return -1.
    // Remove the node from double linked list,
    // and add the node to the head of list.
    // Return the value of node.
    public int get(int key) {
        if (!mp.containsKey(key)) return -1;
        else {
            DoubleListNode cur = mp.get(key);
            remove(cur);
            addToHead(cur);
            return cur.val;
        }
    }
    
    public void set(int key, int value) {
        DoubleListNode cur;
        // New pair will be put in cache
        if (!mp.containsKey(key)) {
            // Create a new node.
            cur = new DoubleListNode(key, value);
            // If the HashMap size equals to capacity,
            // pop the tail node as well as remove the pair in HashMap.
            if (mp.size() == capacity) {
                mp.remove(tail.key);
                remove(tail);
            }
        // Modify existing pair in cache.
        // Change the value in node.
        // Remove the node from list.
        } else {
            cur = mp.get(key);
            cur.val = value;
            remove(cur);
        }
        // Add node to head as most recently used.
        // Also put new pair in HashMap.
        addToHead(cur);
        mp.put(key, cur);
    }
    
    // Add a node to the head of double linked list.
    private void addToHead(DoubleListNode node) {
        // List is empty, node will be both head and tail.
        if (head == null && tail == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }
    
    // Remove the given node from double linked list.
    private void remove(DoubleListNode node) {
        // Node is the only node in double linked list.
        // After remove, the list is empty.
        if (head == node && tail == node) {
            head = null;
            tail = null;
        // Remove the tail of list.
        // New tail is the previous node.
        } else if (tail == node) {
            DoubleListNode newTail = node.prev;
            newTail.next = null;
            node.prev = null;
            tail = newTail;
        // Remove the head of list.
        // New head is the next node.
        } else if (head == node) {
            DoubleListNode newHead = node.next;
            newHead.prev = null;
            node.next = null;
            head = newHead;
        // In middle, connect the previous node and the next node.
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }
    }
}