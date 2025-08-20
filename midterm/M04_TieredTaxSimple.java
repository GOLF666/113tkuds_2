import java.io.*;
import java.util.*;

public class M04_TieredTaxSimple {
    static long tax(long x){
        long t = 0;
        long[] up = {120000, 500000, 1000000};
        int[] rate = {5, 12, 20, 30};
        long prev = 0;
        for(int i=0;i<up.length;i++){
            long hi = Math.min(x, up[i]);
            if(hi > prev){
                t += (hi - prev) * rate[i] / 100;
                prev = hi;
            }
        }
        if(x > prev){
            t += (x - prev) * rate[3] / 100;
        }
        return t;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            long x = Long.parseLong(br.readLine().trim());
            long t = tax(x);
            sum += t;
            sb.append("Tax: ").append(t).append('\n');
        }
        sb.append("Average: ").append(sum / n).append('\n');
        System.out.print(sb.toString());
    }
}

/*
 * Time Complexity: O(n)
 * 說明：每筆收入依固定 4 個級距常數時間計算稅額 O(1)，共 n 筆輸入，總時間 O(n)。
 *      空間為 O(1)（僅常數暫存）。
 */
