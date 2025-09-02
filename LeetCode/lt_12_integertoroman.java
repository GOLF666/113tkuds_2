package LeetCode;

class integertoroman {
    public String intToRoman(int num) {
        // 羅馬數字與對應數值（從大到小排序，含特殊減法符號）
        int[] values = {
            1000, 900, 500, 400, 
            100, 90, 50, 40, 
            10, 9, 5, 4, 1
        };

        String[] symbols = {
            "M", "CM", "D", "CD", 
            "C", "XC", "L", "XL", 
            "X", "IX", "V", "IV", "I"
        };

        StringBuilder roman = new StringBuilder();

        // 從最大值開始檢查，每次減去對應值並加上對應符號
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                roman.append(symbols[i]);
                num -= values[i];
            }
        }

        return roman.toString();
    }
}

/*
解題思路：
1. 根據題意，羅馬數字由大到小組合，使用貪婪策略逐一減去最大可對應的值。
2. 同時處理特殊數字（如 4、9、40、90、400、900）對應的 subtractive form（減法形式）。
3. 每減去一次值，就將對應符號加入結果字串，直到數值為 0。
4. 時間複雜度為 O(1)，因為最大值 3999，最多 15 步處理完。
*/

