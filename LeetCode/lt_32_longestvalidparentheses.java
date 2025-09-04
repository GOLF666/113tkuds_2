import java.util.Stack;

public class lt_32_longestvalidparentheses {

    // 主方法：計算最長有效括號子字串長度
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>(); // 存左括號的位置
        int maxLen = 0;
        int start = -1; // 記錄最後一個無效右括號位置

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i); // 記錄左括號位置
            } else {
                if (stack.isEmpty()) {
                    start = i; // 無匹配的右括號
                } else {
                    stack.pop(); // 配對成功

                    if (stack.isEmpty()) {
                        maxLen = Math.max(maxLen, i - start); // 計算長度（配對到 start 之後）
                    } else {
                        maxLen = Math.max(maxLen, i - stack.peek()); // 計算長度（配對到上一個 '('）
                    }
                }
            }
        }

        return maxLen;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_32_longestvalidparentheses solver = new lt_32_longestvalidparentheses();

        System.out.println(solver.longestValidParentheses("(()"));        // ➜ 2
        System.out.println(solver.longestValidParentheses(")()())"));     // ➜ 4
        System.out.println(solver.longestValidParentheses(""));           // ➜ 0
        System.out.println(solver.longestValidParentheses("(()())"));     // ➜ 6
        System.out.println(solver.longestValidParentheses("())((())"));   // ➜ 4
    }
}

/*
📘 解題說明：
1. 題目要求找出最長的有效括號配對子字串長度（例如 "()()" 長度為 4）。
2. 使用堆疊記錄左括號 '(' 的位置：
   - 如果遇到 '('，就 push 到 stack。
   - 如果遇到 ')'：
     - 若 stack 為空，記錄當前無效的起始點。
     - 若 stack 非空，pop 一個 '('，並用當前索引與前一個未配對位置計算最大長度。
3. 透過 stack.peek() 可得最靠近的未配對 '(' 的位置。
4. 若 stack 為空，代表整段區間配對成功，起點為 start + 1。
*/
