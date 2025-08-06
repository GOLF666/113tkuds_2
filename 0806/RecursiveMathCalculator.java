public class RecursiveMathCalculator {

    // 組合數 C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    // 卡塔蘭數 Catalan(n)
    public static int catalan(int n) {
        if (n <= 1) return 1;
        int res = 0;
        for (int i = 0; i < n; i++)
            res += catalan(i) * catalan(n - 1 - i);
        return res;
    }

    // 漢諾塔步數 Hanoi(n)
    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    // 判斷是否為回文數（數字）
    public static boolean isPalindrome(int n) {
        return n == reverse(n, 0);
    }

    private static int reverse(int n, int rev) {
        if (n == 0) return rev;
        return reverse(n / 10, rev * 10 + n % 10);
    }

    public static void main(String[] args) {
        System.out.println("C(5,2) = " + combination(5, 2));
        System.out.println("Catalan(4) = " + catalan(4));
        System.out.println("Hanoi moves for 3 disks = " + hanoiMoves(3));
        System.out.println("Is 12321 palindrome? " + isPalindrome(12321));
    }
}
