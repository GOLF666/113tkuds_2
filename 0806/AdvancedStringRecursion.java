import java.util.*;

public class AdvancedStringRecursion {

    // 所有排列組合
    public static void permute(String s, String res) {
        if (s.isEmpty()) {
            System.out.println(res);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            permute(s.substring(0, i) + s.substring(i + 1), res + s.charAt(i));
        }
    }

    // 字串匹配（是否為另一字串子字串）
    public static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return true;
        if (text.isEmpty()) return false;
        if (text.charAt(0) == pattern.charAt(0))
            return match(text.substring(1), pattern.substring(1));
        else
            return match(text.substring(1), pattern);
    }

    // 移除重複字符
    public static String removeDup(String s, String seen) {
        if (s.isEmpty()) return "";
        char c = s.charAt(0);
        if (seen.indexOf(c) != -1)
            return removeDup(s.substring(1), seen);
        else
            return c + removeDup(s.substring(1), seen + c);
    }

    // 所有子字串組合
    public static void allSubstrings(String s, String res) {
        if (s.isEmpty()) {
            if (!res.isEmpty()) System.out.println(res);
            return;
        }
        allSubstrings(s.substring(1), res + s.charAt(0)); // include
        allSubstrings(s.substring(1), res);               // exclude
    }

    public static void main(String[] args) {
        System.out.println("字串排列:");
        permute("abc", "");

        System.out.println("\n是否匹配？" + match("abcdef", "ace"));

        System.out.println("\n移除重複: " + removeDup("banana", ""));

        System.out.println("\n所有子字串組合:");
        allSubstrings("abc", "");
    }
}
