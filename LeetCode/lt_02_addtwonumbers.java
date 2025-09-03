
public class lt_02_addtwonumbers {

    // ✅ Linked List 節點定義
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ✅ 主邏輯：相加兩個反向 Linked List
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int val1 = (l1 != null) ? l1.val : 0;
            int val2 = (l2 != null) ? l2.val : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3))); // 342
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4))); // 465

        ListNode result = addTwoNumbers(l1, l2); // 應為 807 → [7,0,8]
        printList(result);
    }

    // 🛠️ 工具方法：列印 Linked List
    public static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val);
            if (node.next != null) System.out.print(" -> ");
            node = node.next;
        }
        System.out.println();
    }
}

/*
解題思路：
1. 使用 dummy node 建立新的鏈結串列。
2. 使用 carry 記錄進位，加總每一位數（包括進位）。
3. 當 l1 或 l2 不為空，或 carry > 0 時持續計算。
4. 每次取出 l1、l2 的值與進位加總，創建新節點記錄個位數。
5. 注意最後可能進位還需額外建立一個節點（如 5+5=10）。
6. 時間複雜度 O(max(m, n))，m、n 為兩串列長度。
*/