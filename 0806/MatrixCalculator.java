public class MatrixCalculator {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2},
            {3, 4}
        };
        int[][] B = {
            {5, 6},
            {7, 8}
        };

        // 加法
        System.out.println("矩陣加法:");
        int[][] sum = new int[2][2];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < A[0].length; j++)
                sum[i][j] = A[i][j] + B[i][j];

        printMatrix(sum);

        // 乘法
        System.out.println("矩陣乘法:");
        int[][] product = new int[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    product[i][j] += A[i][k] * B[k][j];

        printMatrix(product);

        // 轉置
        System.out.println("矩陣A的轉置:");
        int[][] transpose = new int[2][2];
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                transpose[j][i] = A[i][j];

        printMatrix(transpose);

        // 最大與最小值
        int max = A[0][0], min = A[0][0];
        for (int[] row : A)
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        System.out.println("矩陣A最大值: " + max + ", 最小值: " + min);
    }

    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row)
                System.out.print(val + "\t");
            System.out.println();
        }
    }
}
