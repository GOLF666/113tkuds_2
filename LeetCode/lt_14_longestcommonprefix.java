
class lt_14_longestcommonprefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 以第一個字串為基準
        String prefix = strs[0];

        // 從第二個字串開始比對
        for (int i = 1; i < strs.length; i++) {
            // 若當前字串不以 prefix 開頭，縮短 prefix
            while (!strs[i].startsWith(prefix)) {
                prefix = prefix.substring(0, prefix.length() - 1);
                // 若縮短到空字串，代表無共同前綴
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        lt_14_longestcommonprefix solution = new lt_14_longestcommonprefix();
        System.out.println(solution.longestCommonPrefix(new String[]{"flower", "flow", "flight"})); // fl
        System.out.println(solution.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));     // ""
    }
}

/*
解題思路：
1. 先取第一個字串為預設 prefix，從第 2 個字串開始比對。
2. 若當前字串不是以 prefix 開頭，逐步縮短 prefix。
3. 若縮短到空字串代表無共同前綴，提早返回。
4. 時間複雜度為 O(n * m)，n 為字串數量，m 為最短字串長度。
*/
