import java.io.*;
import java.util.*;

public class M01_BuildHeap {
    static int n;
    static long[] a;
    static boolean isMax;

    static boolean better(long x, long y) { // for heap parent vs child
        return isMax ? x > y : x < y;
    }

    static void heapifyDown(int i) {
        while (true) {
            int l = i * 2 + 1, r = i * 2 + 2, best = i;
            if (l < n && better(a[l], a[best])) best = l;
            if (r < n && better(a[r], a[best])) best = r;
            if (best == i) break;
            long tmp = a[i]; a[i] = a[best]; a[best] = tmp;
            i = best;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String type = br.readLine().trim();
        isMax = type.equalsIgnoreCase("max");
        n = Integer.parseInt(br.readLine().trim());
        a = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Long.parseLong(st.nextToken());

        for (int i = (n / 2) - 1; i >= 0; i--) heapifyDown(i);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }
}

/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆，自 i=(n/2-1) 逐一 heapifyDown。第 k 層的節點數與每個節點向下交換的期望次數相抵，
 *      得到 2n 的上界常數因子，因此總時間複雜度為線性 O(n)。
 */
