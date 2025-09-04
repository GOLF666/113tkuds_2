public class lt_38_countandsay {

    // 主方法：取得第 n 項 countAndSay
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String result = "1";

        // 從第 2 項開始生成
        for (int i = 2; i <= n; i++) {
            result = buildNext(result);
        }

        return result;
    }

    // 根據上一個字串構建下一個字串（RLE 壓縮）
    private String buildNext(String s) {
        StringBuilder sb = new StringBuilder();

        int count = 1;
        char prev = s.charAt(0);

        for (int i = 1; i < s.length(); i++) {
            char curr = s.charAt(i);

            if (curr == prev) {
                count++;
            } else {
                sb.append(count).append(prev); // ex: 2 -> "2", '1' -> "1" => "21"
                prev = curr;
                count = 1;
            }
        }

        // 最後一組補上
        sb.append(count).append(prev);

        return sb.toString();
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_38_countandsay solver = new lt_38_countandsay();

        System.out.println(solver.countAndSay(1)); // ➜ "1"
        System.out.println(solver.countAndSay(2)); // ➜ "11"
        System.out.println(solver.countAndSay(3)); // ➜ "21"
        System.out.println(solver.countAndSay(4)); // ➜ "1211"
        System.out.println(solver.countAndSay(5)); // ➜ "111221"
        System.out.println(solver.countAndSay(6)); // ➜ "312211"
        System.out.println(solver.countAndSay(7)); // ➜ "13112221"
    }
}

/*
📘 解題說明：
1. 題目定義一個字串序列 countAndSay(n)，每一項是前一項的「語音壓縮描述」。
2. 每一輪都對上一輪的字串進行 Run-Length Encoding（RLE）：
   - 把連續重複的數字描述成：「次數 + 數字」
   - 例如："3322251" → "23321511"

3. 解法使用迭代方式從 n=1 開始產生到 n（遞迴也可，但迭代更快）。
*/
