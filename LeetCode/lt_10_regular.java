
public class lt_10_regular {

    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 空字串對空模式為 true
        dp[0][0] = true;

        // 處理像 a*, a*b*, a*b*c* 等 pattern 開頭
        for (int j = 2; j <= n; j += 2) {
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        // 動態規劃填表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // 看成 0 次（前一個 pattern 整組不看）
                    dp[i][j] = dp[i][j - 2];

                    // 看成 1 次以上（前一個 pattern 符合當前字元）
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == sc) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a"));         // false
        System.out.println(isMatch("aa", "a*"));        // true
        System.out.println(isMatch("ab", ".*"));        // true
        System.out.println(isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(isMatch("aab", "c*a*b"));    // true
    }
}

/*
解題思路：
1. 使用動態規劃 dp[i][j] 表示 s 的前 i 個字元與 p 的前 j 個字元是否匹配。
2. 若 pattern 為 '.' 或字符相等，則繼承 dp[i-1][j-1]。
3. 若 pattern 為 '*'，可分兩種情況：
   - 當作 0 次出現：跳過 p[j-2] 和 '*'，使用 dp[i][j-2]
   - 當作多次出現：若 p[j-2] 能匹配 s[i-1]，則 dp[i][j] |= dp[i-1][j]
4. 初始化時需處理空字串與前綴如 a*, a*b* 等特殊情況。
5. 時間與空間複雜度均為 O(m * n)，其中 m, n 為 s 與 p 長度。
*/