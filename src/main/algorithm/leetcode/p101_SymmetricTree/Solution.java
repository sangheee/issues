package algorithm.leetcode.p101_SymmetricTree;

import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class Solution {

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode r1, TreeNode r2) {
        if (r1 == null) {
            if (r2 != null)
                return false;
            return true;
        } else if (r2 == null) {
            return false;
        } else if (r1.val != r2.val) {
            return false;
        }
        return isSymmetric(r1.left, r2.right) && isSymmetric(r1.right, r2.left);
    }


    // Timeout Solution
    // max height가 h라고 할 때
    // O(2^h) time complexity, O(n) space complexity

//    public boolean isSymmetric(TreeNode root) {
//        List<TreeNode> oldQueue = new LinkedList<>();
//        oldQueue.add(root);
//
//        boolean more = root != null;
//        for (int i = 1; more; i *= 2) {
//            more = false;
//            List<TreeNode> newQueue = new LinkedList<>();
//            for (int j = 0; j < i; j++) {
//                TreeNode n = oldQueue.remove(0);
//                if (n == null) {
//                    newQueue.add(null);
//                    newQueue.add(null);
//                } else {
//                    newQueue.add(n.left);
//                    newQueue.add(n.right);
//                }
//            }
//
//            for (int j = 0, size = newQueue.size(); j < size / 2; j++) {
//                if (newQueue.get(j) != null) {
//                    more = true;
//                    if (newQueue.get(size - j - 1) == null)
//                        return false;
//
//                    if (newQueue.get(j).val != newQueue.get(size - j - 1).val)
//                        return false;
//                } else if (newQueue.get(size - j - 1) != null)
//                    return false;
//            }
//            oldQueue = newQueue;
//        }
//
//        return true;
//    }

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
        assertTrue(isSymmetric(arrayToTreeNode(new Object[]{})));
        assertFalse(isSymmetric(arrayToTreeNode(new Object[]{1, 2, 2, 2, null, 2})));
        assertFalse(isSymmetric(arrayToTreeNode(new Object[]{1, null, 2})));
        assertTrue(isSymmetric(arrayToTreeNode(new Object[]{1, 2, 2, 3, 4, 4, 3})));
        assertFalse(isSymmetric(arrayToTreeNode(new Object[]{1, 2, 2, null, 3, null, 3})));
    }
}
