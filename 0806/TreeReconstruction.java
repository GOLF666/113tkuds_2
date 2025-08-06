import java.util.*;

public class TreeReconstruction {

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class TreeBuilder {

        public static TreeNode buildFromPreIn(int[] preorder, int[] inorder) {
            Map<Integer, Integer> inMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
            return buildPreIn(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        }

        private static TreeNode buildPreIn(int[] pre, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
            if (ps > pe || is > ie) return null;
            TreeNode root = new TreeNode(pre[ps]);
            int ri = inMap.get(pre[ps]);
            int leftSize = ri - is;
            root.left = buildPreIn(pre, ps + 1, ps + leftSize, in, is, ri - 1, inMap);
            root.right = buildPreIn(pre, ps + leftSize + 1, pe, in, ri + 1, ie, inMap);
            return root;
        }

        public static TreeNode buildFromPostIn(int[] postorder, int[] inorder) {
            Map<Integer, Integer> inMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) inMap.put(inorder[i], i);
            return buildPostIn(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1, inMap);
        }

        private static TreeNode buildPostIn(int[] post, int ps, int pe, int[] in, int is, int ie, Map<Integer, Integer> inMap) {
            if (ps > pe || is > ie) return null;
            TreeNode root = new TreeNode(post[pe]);
            int ri = inMap.get(post[pe]);
            int leftSize = ri - is;
            root.left = buildPostIn(post, ps, ps + leftSize - 1, in, is, ri - 1, inMap);
            root.right = buildPostIn(post, ps + leftSize, pe - 1, in, ri + 1, ie, inMap);
            return root;
        }

        public static TreeNode buildFromLevelOrder(int[] levelOrder) {
            if (levelOrder.length == 0) return null;
            TreeNode root = new TreeNode(levelOrder[0]);
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int i = 1;
            while (i < levelOrder.length) {
                TreeNode node = queue.poll();
                if (i < levelOrder.length) {
                    node.left = new TreeNode(levelOrder[i++]);
                    queue.offer(node.left);
                }
                if (i < levelOrder.length) {
                    node.right = new TreeNode(levelOrder[i++]);
                    queue.offer(node.right);
                }
            }
            return root;
        }

        public static List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            inorderHelper(root, result);
            return result;
        }

        private static void inorderHelper(TreeNode node, List<Integer> list) {
            if (node == null) return;
            inorderHelper(node.left, list);
            list.add(node.val);
            inorderHelper(node.right, list);
        }

        public static boolean isSameTree(TreeNode a, TreeNode b) {
            if (a == null && b == null) return true;
            if (a == null || b == null) return false;
            if (a.val != b.val) return false;
            return isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
        }
    }

    public static void main(String[] args) {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        int[] postorder = {4, 5, 2, 6, 7, 3, 1};
        int[] levelOrder = {1, 2, 3, 4, 5, 6, 7};

        TreeNode root1 = TreeBuilder.buildFromPreIn(preorder, inorder);
        TreeNode root2 = TreeBuilder.buildFromPostIn(postorder, inorder);
        TreeNode root3 = TreeBuilder.buildFromLevelOrder(levelOrder);

        System.out.println("是否相同（Pre/In vs Post/In）：" + TreeBuilder.isSameTree(root1, root2));
        System.out.println("是否相同（Pre/In vs Level）：" + TreeBuilder.isSameTree(root1, root3));
        System.out.println("中序驗證（重建 Pre/In）：" + TreeBuilder.inorderTraversal(root1));
        System.out.println("中序驗證（重建 Post/In）：" + TreeBuilder.inorderTraversal(root2));
        System.out.println("中序驗證（重建 LevelOrder）：" + TreeBuilder.inorderTraversal(root3));
    }
}
