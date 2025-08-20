import java.io.*;
import java.util.*;

public class M09_AVLValidate {
    static int n;
    static long[] a;

    static boolean isNull(int i){ return i>=n || a[i]==-1; }

    static boolean checkBST(int i, long lo, long hi){
        if(isNull(i)) return true;
        long v = a[i];
        if(!(lo < v && v < hi)) return false;
        return checkBST(i*2+1, lo, v) && checkBST(i*2+2, v, hi);
    }

    // 回傳高度；若發現失衡回傳 -INF（用極小值表示）
    static int NEG_INF = Integer.MIN_VALUE/4;
    static int checkHeight(int i){
        if(isNull(i)) return 0;
        int lh = checkHeight(i*2+1); if(lh==NEG_INF) return NEG_INF;
        int rh = checkHeight(i*2+2); if(rh==NEG_INF) return NEG_INF;
        if(Math.abs(lh - rh) > 1) return NEG_INF;
        return Math.max(lh, rh) + 1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        a = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) a[i] = Long.parseLong(st.nextToken());

        boolean bst = checkBST(0, Long.MIN_VALUE, Long.MAX_VALUE);
        if(!bst){
            System.out.println("Invalid BST");
            return;
        }
        int h = checkHeight(0);
        if(h == NEG_INF){
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：BST 檢查與 AVL 平衡檢查各自對每個節點恰一次遞迴訪問，總體仍為線性 O(n)。
 *      空間為遞迴深度 O(h)，最壞 O(n)（退化樹），平均 O(log n）。
 */
