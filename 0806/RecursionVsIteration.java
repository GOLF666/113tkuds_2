public class RecursionVsIteration {

    // 遞迴：二項式係數
    public static int binomialRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRec(n - 1, k - 1) + binomialRec(n - 1, k);
    }

    // 迭代：二項式係數
    public static int binomialIter(int n, int k) {
        int res = 1;
        for (int i = 1; i <= k; i++) {
            res *= n - (k - i);
            res /= i;
        }
        return res;
    }

    // 陣列乘積：遞迴
    public static int productRec(int[] arr, int i) {
        if (i == arr.length) return 1;
        return arr[i] * productRec(arr, i + 1);
    }

    // 陣列乘積：迭代
    public static int productIter(int[] arr) {
        int prod = 1;
        for (int j : arr) prod *= j;
        return prod;
    }

    // 元音數：遞迴
    public static int countVowelsRec(String s, int i) {
        if (i == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(i));
        return (isVowel(c) ? 1 : 0) + countVowelsRec(s, i + 1);
    }

    public static int countVowelsIter(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray())
            if (isVowel(c)) count++;
        return count;
    }

    private static boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }

    // 括號配對檢查（遞迴）
    public static boolean checkParen(String s, int count) {
        if (s.isEmpty()) return count == 0;
        char c = s.charAt(0);
        if (c == '(') return checkParen(s.substring(1), count + 1);
        if (c == ')') return count > 0 && checkParen(s.substring(1), count - 1);
        return checkParen(s.substring(1), count);
    }

    public static void main(String[] args) {
        System.out.println("C(6,3) 遞迴: " + binomialRec(6, 3));
        System.out.println("C(6,3) 迭代: " + binomialIter(6, 3));

        int[] arr = {2, 3, 4};
        System.out.println("乘積(遞迴): " + productRec(arr, 0));
        System.out.println("乘積(迭代): " + productIter(arr));

        String text = "Recursion";
        System.out.println("元音數(遞迴): " + countVowelsRec(text, 0));
        System.out.println("元音數(迭代): " + countVowelsIter(text));

        System.out.println("括號配對: " + checkParen("(a(b)c)", 0));
    }
}
