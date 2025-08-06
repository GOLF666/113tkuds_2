import java.util.*;

public class MultiWayTreeAndDecisionTree {

    // ========== 節點類別：可有任意多個子節點 ==========
    static class MultiNode {
        String value;
        List<MultiNode> children;

        MultiNode(String value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
    }

    static class MultiTree {

        MultiNode root;

        MultiTree(String rootValue) {
            root = new MultiNode(rootValue);
        }

        // 1️⃣ 深度優先走訪（前序）
        public void dfs(MultiNode node) {
            if (node == null) return;
            System.out.print(node.value + " ");
            for (MultiNode child : node.children) {
                dfs(child);
            }
        }

        // 2️⃣ 廣度優先走訪（層序）
        public void bfs(MultiNode root) {
            if (root == null) return;
            Queue<MultiNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                MultiNode node = queue.poll();
                System.out.print(node.value + " ");
                for (MultiNode child : node.children) {
                    queue.offer(child);
                }
            }
        }

        // 3️⃣ 計算樹高
        public int getHeight(MultiNode node) {
            if (node == null) return 0;
            int max = 0;
            for (MultiNode child : node.children) {
                max = Math.max(max, getHeight(child));
            }
            return max + 1;
        }

        // 4️⃣ 計算每個節點的度數（直接列印）
        public void printDegrees(MultiNode node) {
            if (node == null) return;
            System.out.println("節點 [" + node.value + "] 的度數：" + node.children.size());
            for (MultiNode child : node.children) {
                printDegrees(child);
            }
        }
    }

    // ========== 簡單決策樹（猜數字邏輯） ==========
    static class DecisionNode {
        String question;
        Map<String, DecisionNode> options;

        DecisionNode(String question) {
            this.question = question;
            this.options = new HashMap<>();
        }

        public void addOption(String answer, DecisionNode next) {
            options.put(answer.toLowerCase(), next);
        }

        public void play(Scanner sc) {
            DecisionNode current = this;
            while (!current.options.isEmpty()) {
                System.out.println(current.question);
                String input = sc.nextLine().trim().toLowerCase();
                if (current.options.containsKey(input)) {
                    current = current.options.get(input);
                } else {
                    System.out.println("無效選項，請重新輸入。");
                }
            }
            System.out.println("👉 最終結果：" + current.question);
        }
    }

    public static void main(String[] args) {
        // 建立多路樹
        MultiTree tree = new MultiTree("A");
        MultiNode B = new MultiNode("B");
        MultiNode C = new MultiNode("C");
        MultiNode D = new MultiNode("D");
        MultiNode E = new MultiNode("E");
        MultiNode F = new MultiNode("F");
        MultiNode G = new MultiNode("G");
        MultiNode H = new MultiNode("H");

        tree.root.children.add(B);
        tree.root.children.add(C);
        B.children.add(D);
        B.children.add(E);
        C.children.add(F);
        F.children.add(G);
        F.children.add(H);

        System.out.println("✅ 深度優先走訪：");
        tree.dfs(tree.root);
        System.out.println("\n✅ 廣度優先走訪：");
        tree.bfs(tree.root);
        System.out.println("\n✅ 樹的高度：" + tree.getHeight(tree.root));
        System.out.println("✅ 各節點的度數：");
        tree.printDegrees(tree.root);

        // 建立簡單的猜數字決策樹
        System.out.println("\n=== 🧠 猜數字遊戲（決策樹） ===");

        DecisionNode start = new DecisionNode("你選擇的數字小於 5 嗎？(yes/no)");
        DecisionNode q2 = new DecisionNode("你的數字是偶數嗎？(yes/no)");
        DecisionNode q3 = new DecisionNode("你的數字大於 7 嗎？(yes/no)");

        start.addOption("yes", q2);
        start.addOption("no", q3);

        q2.addOption("yes", new DecisionNode("你選的是 2"));
        q2.addOption("no", new DecisionNode("你選的是 3"));

        q3.addOption("yes", new DecisionNode("你選的是 9"));
        q3.addOption("no", new DecisionNode("你選的是 6"));

        Scanner scanner = new Scanner(System.in);
        start.play(scanner);
    }
}
