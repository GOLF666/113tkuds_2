public class TicTacToeBoard {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        initBoard();
        makeMove(0, 0, 'X');
        makeMove(1, 1, 'O');
        makeMove(0, 1, 'X');
        makeMove(2, 2, 'X');
        makeMove(0, 2, 'X'); // X win
        printBoard();
        System.out.println("是否獲勝: " + checkWin('X'));
    }

    static void initBoard() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = '.';
    }

    static boolean makeMove(int row, int col, char player) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != '.') {
            System.out.println("無效的移動！");
            return false;
        }
        board[row][col] = player;
        return true;
    }

    static void printBoard() {
        for (char[] row : board) {
            for (char cell : row)
                System.out.print(cell + " ");
            System.out.println();
        }
    }

    static boolean checkWin(char player) {
        for (int i = 0; i < 3; i++)
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player))
                return true;
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }
}
