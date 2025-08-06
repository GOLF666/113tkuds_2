import java.util.*;

public class BSTRangeQuerySystem {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static class BST {
        TreeNode root;

        public void insert(int val) {
            root = insertRecursive(root, val);
        }

        private TreeNode insertRecursive(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (val < node.val) node.left = insertRecursive(node.left, val);
            else if (val > node.val) node.right = insertRecursive(node.right, val);
            return node;
        }

        public List<Integer> rangeQuery(int min, int max) {
            List<Integer> result = new ArrayList<>();
            rangeQueryHelper(root, min, max, result);
            return result;
        }

        private void rangeQueryHelper(TreeNode node, int min, int max, List<Integer> result) {
            if (node == null) return;
            if (node.val > min) rangeQueryHelper(node.left, min, max, result);
            if (node.val >= min && node.val <= max) result.add(node.val);
            if (node.val < max) rangeQueryHelper(node.right, min, max, result);
        }

        public int rangeCount(int min, int max) {
            return rangeCountHelper(root, min, max);
        }

        private int rangeCountHelper(TreeNode node, int min, int max) {
            if (node == null) return 0;
            if (node.val < min) return rangeCountHelper(node.right, min, max);
            if (node.val > max) return rangeCountHelper(node.left, min, max);
            return 1 + rangeCountHelper(node.left, min, max) + rangeCountHelper(node.right, min, max);
        }

        public int rangeSum(int min, int max) {
            return rangeSumHelper(root, min, max);
        }

        private int rangeSumHelper(TreeNode node, int min, int max) {
            if (node == null) return 0;
            if (node.val < min) return rangeSumHelper(node.right, min, max);
            if (node.val > max) return rangeSumHelper(node.left, min, max);
            return node.val + rangeSumHelper(node.left, min, max) + rangeSumHelper(node.right, min, max);
        }

        public int findClosest(int target) {
            return findClosestHelper(root, target, root.val);
        }

        private int findClosestHelper(TreeNode node, int target, int closest) {
            if (node == null) return closest;
            if (Math.abs(node.val - target) < Math.abs(closest - target)) {
                closest = node.val;
            }
            if (target < node.val) return findClosestHelper(node.left, target, closest);
            else return findClosestHelper(node.right, target, closest);
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        int[] values = {15, 10, 20, 8, 12, 17, 25};
        for (int val : values) {
            tree.insert(val);
        }

        int min = 10, max = 20;
        int target = 16;

        System.out.println("✅ 範圍查詢 [" + min + ", " + max + "]：" + tree.rangeQuery(min, max));
        System.out.println("📊 範圍計數 [" + min + ", " + max + "]：" + tree.rangeCount(min, max));
        System.out.println("➕ 範圍總和 [" + min + ", " + max + "]：" + tree.rangeSum(min, max));
        System.out.println("🎯 最接近 " + target + " 的節點值：" + tree.findClosest(target));
    }
}
