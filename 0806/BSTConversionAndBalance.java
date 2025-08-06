import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

class DLLNode {
    int val;
    DLLNode prev, next;

    DLLNode(int val) {
        this.val = val;
    }
}

public class BSTConversionAndBalance {

    TreeNode root;

    public BSTConversionAndBalance(int[] values) {
        for (int val : values) {
            root = insert(root, val);
        }
    }

    TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insert(node.left, val);
        else node.right = insert(node.right, val);
        return node;
    }

    DLLNode bstToSortedDLL(TreeNode root) {
        return bstToDllHelper(root)[0];
    }

    DLLNode[] bstToDllHelper(TreeNode root) {
        if (root == null) return new DLLNode[]{null, null};
        DLLNode[] left = bstToDllHelper(root.left);
        DLLNode curr = new DLLNode(root.val);
        DLLNode[] right = bstToDllHelper(root.right);

        if (left[1] != null) {
            left[1].next = curr;
            curr.prev = left[1];
        }

        if (right[0] != null) {
            curr.next = right[0];
            right[0].prev = curr;
        }

        DLLNode head = (left[0] != null) ? left[0] : curr;
        DLLNode tail = (right[1] != null) ? right[1] : curr;
        return new DLLNode[]{head, tail};
    }

    TreeNode sortedArrayToBST(int[] arr) {
        return arrayToBST(arr, 0, arr.length - 1);
    }

    TreeNode arrayToBST(int[] arr, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = arrayToBST(arr, l, mid - 1);
        node.right = arrayToBST(arr, mid + 1, r);
        return node;
    }

    boolean isBalanced(TreeNode node) {
        return checkBalance(node) != -1;
    }

    int checkBalance(TreeNode node) {
        if (node == null) return 0;
        int left = checkBalance(node.left);
        if (left == -1) return -1;
        int right = checkBalance(node.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    void convertToGreaterSum(TreeNode root) {
        sum = 0;
        convertHelper(root);
    }

    int sum = 0;

    void convertHelper(TreeNode node) {
        if (node == null) return;
        convertHelper(node.right);
        sum += node.val;
        node.val = sum;
        convertHelper(node.left);
    }

    void printDLL(DLLNode head) {
        DLLNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

    public static void main(String[] args) {
        int[] values = {5, 3, 7, 2, 4, 6, 8};
        BSTConversionAndBalance bst = new BSTConversionAndBalance(values);

        DLLNode head = bst.bstToSortedDLL(bst.root);
        System.out.print("BST to Sorted DLL: ");
        bst.printDLL(head);

        int[] sorted = {2, 3, 4, 5, 6, 7, 8};
        TreeNode balancedRoot = bst.sortedArrayToBST(sorted);
        System.out.print("Balanced BST Inorder: ");
        bst.inorder(balancedRoot);
        System.out.println();

        System.out.println("Is Balanced: " + bst.isBalanced(balancedRoot));

        bst.convertToGreaterSum(bst.root);
        System.out.print("Greater Sum Tree Inorder: ");
        bst.inorder(bst.root);
        System.out.println();
    }
}
