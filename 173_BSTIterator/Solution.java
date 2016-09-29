/*
    Binary Search Tree Iterator

    Implement an iterator over a binary search tree (BST).
    Your iterator will be initialized with the root node of a BST.

    Calling next() will return the next smallest number in the BST.

    Note: next() and hasNext() should run in average O(1) time and uses O(h) memory,
    where h is the height of the tree.

    Solution:
    Use a stack.
    Push all the nodes along left from root.
    Pop a node and its value is next smallest number.
    Also, when popping a node, starting from its right child, push all the nodes along left.
    When the stack is empty, it has no next value.

    Yu Fu, 09/28/2016
 */

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */

public class BSTIterator {
    Stack<TreeNode> st;
    public BSTIterator(TreeNode root) {
        // At initialzation, push all the nodes along left from root.
        st = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null) {
            st.push(cur);
            cur = cur.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        // Iterator has next smallest number if stack is not empty.
        return !st.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        // Pop a node from stack. Its value is the result.
        TreeNode nextNode = st.pop();
        // If the poped node has right child,
        // starting from its right child,
        // push all the nodes along left.
        TreeNode cur = nextNode.right;
        while (cur != null) {
            st.push(cur);
            cur = cur.left;
        }
        return nextNode.val;
    }
}

