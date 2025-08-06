import java.util.*;

public class TreePathProblems {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class TreePathUtils {

        public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            dfsPaths(root, new ArrayList<>(), result);
            return result;
        }

        private static void dfsPaths(TreeNode node, List<Integer> path, List<List<Integer>> result) {
            if (node == null) return;
            path.add(node.val);
            if (node.left == null && node.right == null) {
                result.add(new ArrayList<>(path));
            } else {
                dfsPaths(node.left, path, result);
                dfsPaths(node.right, path, result);
            }
            path.remove(path.size() - 1);
        }

        public static boolean hasPathSum(TreeNode root, int targetSum) {
            if (root == null) return false;
            if (root.left == null && root.right == null) return root.val == targetSum;
            return hasPathSum(root.left, targetSum - root.val) ||
                   hasPathSum(root.right, targetSum - root.val);
        }

        public static List<Integer> maxRootToLeafPath(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            maxPathHelper(root, 0, new ArrayList<>(), result, new int[]{Integer.MIN_VALUE});
            return result;
        }

        private static void maxPathHelper(TreeNode node, int sum, List<Integer> path, List<Integer> bestPath, int[] maxSum) {
            if (node == null) return;
            path.add(node.val);
            sum += node.val;

            if (node.left == null && node.right == null) {
                if (sum > maxSum[0]) {
                    maxSum[0] = sum;
                    bestPath.clear();
                    bestPath.addAll(path);
                }
            } else {
                maxPathHelper(node.left, sum, path, bestPath, maxSum);
                maxPathHelper(node.right, sum, path, bestPath, maxSum);
            }

            path.remove(path.size() - 1);
        }

        public static int maxPathSum(TreeNode root) {
            int[] max = new int[]{Integer.MIN_VALUE};
            calcMaxPath(root, max);
            return max[0];
        }

        private static int calcMaxPath(TreeNode node, int[] max) {
            if (node == null) return 0;
            int left = Math.max(0, calcMaxPath(node.left, max));
            int right = Math.max(0, calcMaxPath(node.right, max));
            max[0] = Math.max(max[0], left + right + node.val);
            return Math.max(left, right) + node.val;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("所有根到葉路徑：" + TreePathUtils.allRootToLeafPaths(root));
        System.out.println("是否存在總和為 22 的路徑：" + TreePathUtils.hasPathSum(root, 22));
        System.out.println("最大總和根到葉路徑：" + TreePathUtils.maxRootToLeafPath(root));
        System.out.println("任意兩節點最大路徑總和：" + TreePathUtils.maxPathSum(root));
    }
}
