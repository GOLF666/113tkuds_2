public class TreeMirrorAndSymmetry {

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

        public boolean isSymmetric() {
            return isMirror(root, root);
        }

        private boolean isMirror(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) return true;
            if (t1 == null || t2 == null) return false;
            return (t1.val == t2.val)
                    && isMirror(t1.left, t2.right)
                    && isMirror(t1.right, t2.left);
        }

        public void mirror() {
            root = mirrorTree(root);
        }

        private TreeNode mirrorTree(TreeNode node) {
            if (node == null) return null;
            TreeNode left = mirrorTree(node.left);
            TreeNode right = mirrorTree(node.right);
            node.left = right;
            node.right = left;
            return node;
        }

        public static boolean areMirror(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) return true;
            if (t1 == null || t2 == null) return false;
            return (t1.val == t2.val)
                    && areMirror(t1.left, t2.right)
                    && areMirror(t1.right, t2.left);
        }

        public static boolean isSubtree(TreeNode main, TreeNode sub) {
            if (sub == null) return true;
            if (main == null) return false;
            if (isSameTree(main, sub)) return true;
            return isSubtree(main.left, sub) || isSubtree(main.right, sub);
        }

        private static boolean isSameTree(TreeNode t1, TreeNode t2) {
            if (t1 == null && t2 == null) return true;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            return isSameTree(t1.left, t2.left) && isSameTree(t1.right, t2.right);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(2);
        tree.root.left.left = new TreeNode(3);
        tree.root.left.right = new TreeNode(4);
        tree.root.right.left = new TreeNode(4);
        tree.root.right.right = new TreeNode(3);

        System.out.println("是否對稱樹：" + tree.isSymmetric());

        tree.mirror();
        System.out.println("鏡像後是否對稱：" + tree.isSymmetric());

        TreeNode other = new TreeNode(1);
        other.left = new TreeNode(2);
        other.right = new TreeNode(2);
        other.left.left = new TreeNode(4);
        other.left.right = new TreeNode(3);
        other.right.left = new TreeNode(3);
        other.right.right = new TreeNode(4);

        System.out.println("是否互為鏡像：" + BinaryTree.areMirror(tree.root, other));

        TreeNode sub = new TreeNode(2);
        sub.left = new TreeNode(4);
        sub.right = new TreeNode(3);
        System.out.println("是否為子樹：" + BinaryTree.isSubtree(tree.root, sub));
    }
}
