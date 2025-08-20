import java.io.*;
import java.util.*;

public class M05_GCD_LCM_Recursive {
    static long gcd(long a, long b){
        if(b==0) return a;
        return gcd(b, a % b);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long g = gcd(a, b);
        long l = (a / g) * b; // 先除後乘避免溢位
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }
}

/*
 * Time Complexity: O(log min(a,b))
 * 說明：遞迴歐幾里得算法，每次將 (a,b) 變成 (b, a%b)，數學上證明其步數為對數級。
 *      LCM 以 a/g*b 常數時間計算。
 */
