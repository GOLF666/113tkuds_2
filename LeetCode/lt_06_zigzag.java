
public class lt_06_zigzag {

    public static String convert(String s, int numRows) {
        if (numRows == 1 || s.length() <= numRows) return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int currRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows[currRow].append(c);

            if (currRow == 0 || currRow == numRows - 1) {
                goingDown = !goingDown;
            }

            currRow += goingDown ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

    // 🧪 測試用 main 方法
    public static void main(String[] args) {
        String s1 = "PAYPALISHIRING";
        int rows1 = 3;
        System.out.println("Zigzag Result 1: " + convert(s1, rows1)); // PAHNAPLSIIGYIR

        int rows2 = 4;
        System.out.println("Zigzag Result 2: " + convert(s1, rows2)); // PINALSIGYAHRPI

        String s2 = "A";
        int rows3 = 1;
        System.out.println("Zigzag Result 3: " + convert(s2, rows3)); // A
    }
}

/*
解題思路：
1. 將每個字元依照 Zigzag 順序分配到對應的行。
2. 當 currRow 為 0 或 numRows - 1 時，改變方向（由下轉上或由上轉下）。
3. 使用 StringBuilder[] 陣列來儲存每行資料。
4. 最後將每行的字串依序合併成最終結果。
5. 時間複雜度 O(n)，空間複雜度 O(n)，其中 n 為字串長度。
*/