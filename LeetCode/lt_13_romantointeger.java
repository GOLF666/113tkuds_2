
import java.util.*;

class lt_13_romantointeger {
    public int romanToInt(String s) {
        // 建立羅馬符號對應的整數值對照表
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1); map.put('V', 5); map.put('X', 10);
        map.put('L', 50); map.put('C', 100);
        map.put('D', 500); map.put('M', 1000);

        int total = 0;
        int prev = 0;

        // 從右往左遍歷字串
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            // 若當前數字小於右邊，則為減法；否則為加法
            if (curr < prev) {
                total -= curr;
            } else {
                total += curr;
            }
            prev = curr;
        }

        return total;
    }

    public static void main(String[] args) {
        lt_13_romantointeger solution = new lt_13_romantointeger();
        System.out.println(solution.romanToInt("III"));       // 3
        System.out.println(solution.romanToInt("LVIII"));     // 58
        System.out.println(solution.romanToInt("MCMXCIV"));   // 1994
    }
}

/*
解題思路：
1. 從右往左掃描字串，依照羅馬符號對應的數值進行加總。
2. 若遇到前一位數字 < 當前數字，則為減法情形（如 IV = 5 - 1）。
3. 反之則為加法情形（如 VI = 5 + 1）。
4. 時間複雜度為 O(n)，其中 n 為字串長度。
*/
