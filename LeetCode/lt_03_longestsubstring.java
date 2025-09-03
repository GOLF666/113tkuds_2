
import java.util.HashSet;
import java.util.Set;

public class lt_03_longestsubstring {

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0, maxLen = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            if (!set.contains(c)) {
                set.add(c);
                maxLen = Math.max(maxLen, right - left + 1);
                right++;
            } else {
                set.remove(s.charAt(left));
                left++;
            }
        }

        return maxLen;
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        String test1 = "abcabcbb";
        String test2 = "bbbbb";
        String test3 = "pwwkew";

        System.out.println(lengthOfLongestSubstring(test1)); // 3
        System.out.println(lengthOfLongestSubstring(test2)); // 1
        System.out.println(lengthOfLongestSubstring(test3)); // 3
    }
}

/*
解題思路：
1. 使用滑動視窗（雙指標）策略維護一個不含重複字元的子字串。
2. 當遇到新字元時加入 set，並嘗試更新最大長度。
3. 若遇到重複字元，從左邊開始逐個移除，直到無重複為止。
4. 時間複雜度為 O(n)，空間複雜度為 O(k)，k 為字元種類數。
*/