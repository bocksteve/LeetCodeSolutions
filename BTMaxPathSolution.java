/* Leetcode.com Problem #124 (https://leetcode.com/submissions/detail/149747913/)

Description:
Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child 
connections. The path must contain at least one node and does not need to go through the root.*/

/* Strategy: Traverse the tree recursively, returning the max of either left subtrees max path or right subtrees max path.  Time complexity
 * is O(n).
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
class BTMaxPathSolution {
    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        maxPath(root);
        return max;
    }
    
    public int maxPath(TreeNode root) {
        if (root == null) return 0;
        int l, r;
        l = Math.max(0, maxPath(root.left));
        r = Math.max(0, maxPath(root.right));
        max = Math.max(max, l + r + root.val);
        return Math.max(l, r) + root.val;
    }
}