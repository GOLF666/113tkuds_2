import java.util.*;

public class BSTKthElement {

    static class TreeNode {
        int val, size;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.size = 1;
        }
    }

    static class AugmentedBST {
        TreeNode root;

        public void insert(int val) {
            root = insert(root, val);
        }

        private TreeNode insert(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (val < node.val) node.left = insert(node.left, val);
            else node.right = insert(node.right, val);
            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
        }

        public void delete(int val) {
            root = delete(root, val);
        }

        private TreeNode delete(TreeNode node, int val) {
            if (node == null) return null;
            if (val < node.val) node.left = delete(node.left, val);
            else if (val > node.val) node.right = delete(node.right, val);
            else {
                if (node.left == null) return node.right;
                if (node.right == null) return node.left;
                TreeNode minNode = getMin(node.right);
                node.val = minNode.val;
                node.right = delete(node.right, minNode.val);
            }
            node.size = 1 + getSize(node.left) + getSize(node.right);
            return node;
        }

        private TreeNode getMin(TreeNode node) {
            while (node.left != null) node = node.left;
            return node;
        }

        private int getSize(TreeNode node) {
            return node == null ? 0 : node.size;
        }

        public int findKthSmallest(int k) {
            return findKth(root, k);
        }

        private int findKth(TreeNode node, int k) {
            if (node == null) throw new IllegalArgumentException("k 超出範圍");
            int leftSize = getSize(node.left);
            if (k <= leftSize) return findKth(node.left, k);
            else if (k == leftSize + 1) return node.val;
            else return findKth(node.right, k - leftSize - 1);
        }

        public int findKthLargest(int k) {
            return findKthLargest(root, k);
        }

        private int findKthLargest(TreeNode node, int k) {
            int total = getSize(node);
            return findKth(node, total - k + 1);
        }

        public List<Integer> findKthToJthSmallest(int k, int j) {
            List<Integer> result = new ArrayList<>();
            inorderRange(root, k, j, new int[]{0}, result);
            return result;
        }

        private void inorderRange(TreeNode node, int k, int j, int[] count, List<Integer> result) {
            if (node == null || count[0] >= j) return;
            inorderRange(node.left, k, j, count, result);
            count[0]++;
            if (count[0] >= k && count[0] <= j) result.add(node.val);
            inorderRange(node.right, k, j, count, result);
        }
    }

    public static void main(String[] args) {
        AugmentedBST tree = new AugmentedBST();
        int[] nums = {20, 10, 30, 5, 15, 25, 35};
        for (int val : nums) tree.insert(val);

        System.out.println("第 3 小元素：" + tree.findKthSmallest(3));
        System.out.println("第 2 大元素：" + tree.findKthLargest(2));
        System.out.println("第 2 到 5 小元素：" + tree.findKthToJthSmallest(2, 5));

        tree.insert(12);
        tree.insert(28);
        System.out.println("插入後第 4 小元素：" + tree.findKthSmallest(4));

        tree.delete(15);
        System.out.println("刪除 15 後第 4 小元素：" + tree.findKthSmallest(4));
    }
}
