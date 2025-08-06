import java.util.*;

public class BSTValidationAndRepair {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class BSTUtils {

        public static boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private static boolean isValidBST(TreeNode node, long min, long max) {
            if (node == null) return true;
            if (node.val <= min || node.val >= max) return false;
            return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
        }

        public static List<TreeNode> findInvalidNodes(TreeNode root) {
            List<TreeNode> invalidNodes = new ArrayList<>();
            findInvalidHelper(root, Long.MIN_VALUE, Long.MAX_VALUE, invalidNodes);
            return invalidNodes;
        }

        private static void findInvalidHelper(TreeNode node, long min, long max, List<TreeNode> result) {
            if (node == null) return;
            if (node.val <= min || node.val >= max) result.add(node);
            findInvalidHelper(node.left, min, node.val, result);
            findInvalidHelper(node.right, node.val, max, result);
        }

        public static void recoverBST(TreeNode root) {
            TreeNode[] nodes = new TreeNode[2];
            TreeNode[] prev = new TreeNode[1];
            recoverHelper(root, prev, nodes);

            if (nodes[0] != null && nodes[1] != null) {
                int temp = nodes[0].val;
                nodes[0].val = nodes[1].val;
                nodes[1].val = temp;
            }
        }

        private static void recoverHelper(TreeNode node, TreeNode[] prev, TreeNode[] nodes) {
            if (node == null) return;
            recoverHelper(node.left, prev, nodes);

            if (prev[0] != null && prev[0].val > node.val) {
                if (nodes[0] == null) {
                    nodes[0] = prev[0];
                    nodes[1] = node;
                } else {
                    nodes[1] = node;
                }
            }
            prev[0] = node;

            recoverHelper(node.right, prev, nodes);
        }

        public static int minRemovalsToBST(TreeNode root) {
            return countRemovals(root, Long.MIN_VALUE, Long.MAX_VALUE).removals;
        }

        static class Result {
            int size, removals;
            Result(int size, int removals) {
                this.size = size;
                this.removals = removals;
            }
        }

        private static Result countRemovals(TreeNode node, long min, long max) {
            if (node == null) return new Result(0, 0);

            Result left = countRemovals(node.left, min, node.val);
            Result right = countRemovals(node.right, node.val, max);

            if (node.val <= min || node.val >= max) {
                return new Result(0, left.removals + right.removals + left.size + right.size + 1);
            }

            return new Result(1 + left.size + right.size, left.removals + right.removals);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(20); // 錯誤節點
        root.right.left = new TreeNode(6);  // 錯誤節點
        root.right.right = new TreeNode(30);

        System.out.println("是否為合法BST：" + BSTUtils.isValidBST(root));

        List<TreeNode> invalidNodes = BSTUtils.findInvalidNodes(root);
        System.out.print("不合法節點：");
        for (TreeNode node : invalidNodes) System.out.print(node.val + " ");
        System.out.println();

        System.out.println("需要移除的節點數量：" + BSTUtils.minRemovalsToBST(root));

        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(8);
        root2.right = new TreeNode(12);
        root2.left.right = new TreeNode(9);
        root2.right.left = new TreeNode(7); // 與 9 互換

        System.out.println("修復前是否為合法BST：" + BSTUtils.isValidBST(root2));
        BSTUtils.recoverBST(root2);
        System.out.println("修復後是否為合法BST：" + BSTUtils.isValidBST(root2));
    }
}
