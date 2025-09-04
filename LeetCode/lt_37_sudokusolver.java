public class lt_37_sudokusolver {

    // 主方法：解 Sudoku（就地修改 board）
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    // 回溯法主體
    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == '.') {
                    // 嘗試填入 '1' ~ '9'
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, row, col, c)) {
                            board[row][col] = c;

                            if (solve(board)) {
                                return true; // 成功
                            }

                            // 回溯：還原
                            board[row][col] = '.';
                        }
                    }
                    return false; // 九個都不行，回溯上一層
                }
            }
        }
        return true; // 全部填完，合法
    }

    // 檢查填入的數字是否合法
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {
            // 檢查 row
            if (board[row][i] == c) return false;

            // 檢查 column
            if (board[i][col] == c) return false;

            // 檢查 3x3 小區塊
            int boxRow = 3 * (row / 3) + i / 3;
            int boxCol = 3 * (col / 3) + i % 3;
            if (board[boxRow][boxCol] == c) return false;
        }
        return true;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_37_sudokusolver solver = new lt_37_sudokusolver();

        char[][] board = {
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

        solver.solveSudoku(board);

        // 印出結果
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}

/*
📘 解題說明：
1. 題目要求你將部分填寫的 Sudoku 補成完整且合法的解（只有一個解）。
2. 使用 Backtracking：
   - 遍歷棋盤，找空格 '.'
   - 嘗試填入 '1' ~ '9'，若符合 Sudoku 規則就繼續遞迴
   - 若全部格子都合法填完，就 return true
   - 若某步驟無解，則 backtrack 回上一層換數字

3. isValid() 函式用來檢查：
   - 同一 row 不能有重複
   - 同一 column 不能有重複
   - 同一 3x3 box 不能有重複
*/
