import java.util.*;

public class LevelOrderTraversalVariations {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }

    static class Traversal {

        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> level = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                result.add(level);
            }

            return result;
        }

        public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            boolean leftToRight = true;

            while (!queue.isEmpty()) {
                int size = queue.size();
                LinkedList<Integer> level = new LinkedList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (leftToRight) level.addLast(node.val);
                    else level.addFirst(node.val);
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                result.add(level);
                leftToRight = !leftToRight;
            }

            return result;
        }

        public static List<Integer> rightmostEachLevel(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    if (i == size - 1) result.add(node.val);
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
            }

            return result;
        }

        public static List<List<Integer>> verticalOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) return result;

            TreeMap<Integer, List<Integer>> map = new TreeMap<>();
            Queue<Pair> queue = new LinkedList<>();
            queue.offer(new Pair(root, 0));

            while (!queue.isEmpty()) {
                Pair p = queue.poll();
                TreeNode node = p.node;
                int col = p.col;

                map.putIfAbsent(col, new ArrayList<>());
                map.get(col).add(node.val);

                if (node.left != null) queue.offer(new Pair(node.left, col - 1));
                if (node.right != null) queue.offer(new Pair(node.right, col + 1));
            }

            result.addAll(map.values());
            return result;
        }

        static class Pair {
            TreeNode node;
            int col;
            Pair(TreeNode node, int col) {
                this.node = node;
                this.col = col;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("層序走訪分層： " + Traversal.levelOrder(root));
        System.out.println("之字形層序走訪： " + Traversal.zigzagLevelOrder(root));
        System.out.println("每層最後一個節點： " + Traversal.rightmostEachLevel(root));
        System.out.println("垂直層序走訪： " + Traversal.verticalOrder(root));
    }
}
