import java.util.*;

public class BinaryTreeBasicOperations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static class BinaryTree {
        TreeNode root;

        public void insert(int val) {
            root = insertRecursive(root, val);
        }

        private TreeNode insertRecursive(TreeNode node, int val) {
            if (node == null) return new TreeNode(val);
            if (val < node.val) node.left = insertRecursive(node.left, val);
            else node.right = insertRecursive(node.right, val);
            return node;
        }

        public int totalSum() {
            return sumHelper(root);
        }

        private int sumHelper(TreeNode node) {
            if (node == null) return 0;
            return node.val + sumHelper(node.left) + sumHelper(node.right);
        }

        public double average() {
            int[] result = countAndSum(root);
            return result[1] == 0 ? 0 : (double) result[0] / result[1];
        }

        private int[] countAndSum(TreeNode node) {
            if (node == null) return new int[]{0, 0};
            int[] left = countAndSum(node.left);
            int[] right = countAndSum(node.right);
            int sum = node.val + left[0] + right[0];
            int count = 1 + left[1] + right[1];
            return new int[]{sum, count};
        }

        public int findMax() {
            TreeNode current = root;
            while (current.right != null) current = current.right;
            return current.val;
        }

        public int findMin() {
            TreeNode current = root;
            while (current.left != null) current = current.left;
            return current.val;
        }

        public int maxWidth() {
            if (root == null) return 0;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int max = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                max = Math.max(max, size);
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }
            return max;
        }

        public boolean isComplete() {
            if (root == null) return true;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean foundNull = false;
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    foundNull = true;
                } else {
                    if (foundNull) return false;
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        int[] values = {10, 5, 15, 3, 7, 12, 18};
        for (int val : values) tree.insert(val);

        System.out.println("總和：" + tree.totalSum());
        System.out.println("平均值：" + tree.average());
        System.out.println("最大值：" + tree.findMax());
        System.out.println("最小值：" + tree.findMin());
        System.out.println("最大寬度：" + tree.maxWidth());
        System.out.println("是否為完全二元樹：" + tree.isComplete());
    }
}
