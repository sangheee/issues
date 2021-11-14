package algorithm.leetcode.p110_BalancedBinaryTree;

import org.testng.annotations.Test;


import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class Solution {
    class Max {
        int max;
        boolean balanced;

        Max(int max) {
            this.max = max;
            this.balanced = true;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return getMaxHeight(0, root).balanced;
    }

    private Max getMaxHeight(int curr, TreeNode root) {
        if (root == null) {
            return new Max(curr);
        }
        Max left = getMaxHeight(curr + 1, root.left);
        Max right = getMaxHeight(curr + 1, root.right);

        if (!left.balanced)
            return left;
        if (!right.balanced)
            return right;

        Max m = new Max(Math.max(left.max, right.max));
        if (Math.abs(left.max - right.max) > 1)
            m.balanced = false;
        return m;
    }

    public static TreeNode arrayToTreeNode(Object[] nodes) {
        TreeNode[] treeNodes = new TreeNode[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            treeNodes[i] = nodes[i] != null ? new TreeNode((Integer) nodes[i]) : null;
        }
        for (int i = 0; i < treeNodes.length / 2; i++) {
            if (treeNodes[i] == null)
                continue;
            treeNodes[i].left = i * 2 + 1 < treeNodes.length ? treeNodes[i * 2 + 1] : null;
            treeNodes[i].right = i * 2 + 2 < treeNodes.length ? treeNodes[i * 2 + 2] : null;
        }

        return treeNodes.length == 0 ? null : treeNodes[0];
    }

    @Test
    public void test() {
        assertTrue(isBalanced(arrayToTreeNode(new Object[]{})));
        assertTrue(isBalanced(arrayToTreeNode(new Object[]{3, 9, 20, null, null, 15, 7})));
        assertFalse(isBalanced(arrayToTreeNode(new Object[]{1, 2, 2, 3, 3, null, null, 4, 4})));
        assertFalse(isBalanced(arrayToTreeNode(new Object[]{1, 2, 2, 3, null, null, 3, 4, null, null, 4})));
        assertTrue(isBalanced(arrayToTreeNode(new Object[]{1, 2, 3, 4, 5, 6, null, 8})));
    }
}

