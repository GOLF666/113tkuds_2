
public class lt_07_reverse {
    public int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;

            // 判斷是否會溢位
            if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;

            rev = rev * 10 + pop;
        }
        return rev;
    }
}

/*
解題思路：
1. 每次從 x 中取出個位數（x % 10），累加到 rev 的末尾。
2. 加入前先檢查 rev 是否會因 *10 + pop 而溢位。
3. 若溢位就回傳 0（依題意要求）。
4. 正整數溢位上限為 2147483647，負整數下限為 -2147483648。
5. 時間複雜度 O(log₁₀x)，空間複雜度 O(1)。
*/