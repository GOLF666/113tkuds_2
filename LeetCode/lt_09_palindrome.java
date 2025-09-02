package LeetCode;

public class lt_09_palindrome {

    public static boolean isPalindrome(int x) {
        // 負數不是回文；數字尾數為 0 也不是（除非是 0 本身）
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversed = 0;

        // 只反轉一半就能判斷
        while (x > reversed) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        // 偶數位：x == reversed；奇數位：x == reversed/10（忽略中間數字）
        return x == reversed || x == reversed / 10;
    }

    // 🧪 測試用 main 方法
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));      // true
        System.out.println(isPalindrome(-121));     // false
        System.out.println(isPalindrome(10));       // false
        System.out.println(isPalindrome(12321));    // true
        System.out.println(isPalindrome(0));        // true
    }
}

/*
解題思路：
1. 回文數定義：正著讀與反著讀相同。
2. 排除負數與非 0 結尾為 0 的數（如 10）一定不是回文。
3. 使用「反轉一半」技巧避免整型溢位問題：
   - 每次將末位加到 reversed 裡。
   - 當 reversed ≥ x 時表示已處理一半。
4. 最後判斷：
   - 偶數位：x == reversed
   - 奇數位：x == reversed / 10（忽略中間那一位）
5. 時間複雜度 O(log₁₀n)，空間複雜度 O(1)
*/