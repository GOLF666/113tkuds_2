import java.util.HashSet;

public class lt_36_validsudoku {

    // 主方法：驗證數獨是否合法
    public boolean isValidSudoku(char[][] board) {
        // 建立 3 個 hash set 陣列，分別紀錄 row, col, box 中是否出現過數字
        HashSet<Character>[] rows = new HashSet[9];
        HashSet<Character>[] cols = new HashSet[9];
        HashSet<Character>[] boxes = new HashSet[9];

        // 初始化每個 row, col, box 為獨立的 HashSet
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }

        // 遍歷每個格子
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];

                if (val == '.') continue; // 空格跳過

                // 計算這格屬於哪個 box（0 ~ 8）
                int boxIndex = (r / 3) * 3 + (c / 3);

                // 驗證是否已存在
                if (rows[r].contains(val) || cols[c].contains(val) || boxes[boxIndex].contains(val)) {
                    return false;
                }

                // 記錄該數字
                rows[r].add(val);
                cols[c].add(val);
                boxes[boxIndex].add(val);
            }
        }

        return true; // 所有格子都合法
    }

    // main 測試方法
    public static void main(String[] args) {
        lt_36_validsudoku solver = new lt_36_validsudoku();

        // 測試案例 1：有效數獨
        char[][] board1 = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(solver.isValidSudoku(board1)); // ➜ true

        // 測試案例 2：無效數獨（重複 8）
        char[][] board2 = {
            {'8','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        System.out.println(solver.isValidSudoku(board2)); // ➜ false
    }
}

/*
📘 解題說明：
1. 數獨規則要求：
   - 每列（row）不能有重複數字
   - 每欄（column）不能有重複數字
   - 每個 3x3 小九宮格（box）也不能重複
2. 使用 3 個 HashSet 陣列分別追蹤：
   - rows[0~8]
   - cols[0~8]
   - boxes[0~8]（以 boxIndex 計算 box 序號）

3. 若任一位置出現重複（同 row, col, 或 box），立即 return false。
*/
