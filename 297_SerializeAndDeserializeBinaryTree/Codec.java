/*
    Serialize and Deserialize Binary Tree

    Serialization is the process of converting a data structure or object
    into a sequence of bits so that it can be stored in a file or memory buffer,
    or transmitted across a network connection link
    to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary tree.
    There is no restriction on how your serialization/deserialization algorithm should work.
    You just need to ensure that a binary tree can be serialized to a string
    and this string can be deserialized to the original tree structure.

    For example, you may serialize the following tree

        1
       / \
      2   3
         / \
        4   5

    as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree.
    You do not necessarily need to follow this format,
    so please be creative and come up with different approaches yourself.

    Note: Do not use class member/global/static variables to store states.
    Your serialize and deserialize algorithms should be stateless.
    
    Solution:
    Use in-order trasveral to serialize the tree,
    use '#' to mark null node.
    Use recursion (DFS) to serialize and deserialize.

    Yu Fu, 12/01/2016
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> serial = new ArrayList<String>();
        serializeHelper(root, serial);
        StringBuilder sb = new StringBuilder();
        for (String s : serial) {
            sb.append(s);
            sb.append(',');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
    
    // In-order trasverse the tree and add each node to list.
    private void serializeHelper(TreeNode root, List<String> serial) {
        if (root == null) serial.add("#");   // Add '#' if null
        else {
            serial.add(root.val + "");
            serializeHelper(root.left, serial);
            serializeHelper(root.right, serial);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> serial = new ArrayList<String>(Arrays.asList(data.split(",")));
        TreeNode root = deserializeHelper(serial);
        return root;
    }
    
    // Create a node and remove it from serial string.
    // Then recursively reconstruct left and right nodes.
    private TreeNode deserializeHelper(List<String> serial) {
        // Stop recursion at empty serial string or '#'.
        if (serial.size() == 0) return null;
        String s = serial.get(0);
        if (s.equals("#")) return null;
        
        // Recursively reconstruct tree.
        TreeNode cur = new TreeNode(Integer.parseInt(s));
        serial.remove(0);
        cur.left = deserializeHelper(serial);
        cur.right = deserializeHelper(serial);
        return cur;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));