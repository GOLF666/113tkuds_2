import java.io.*;
import java.util.*;

public class M02_YouBikeNextArrival {
    static int toMin(String s){
        s = s.trim();
        String[] p = s.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
    static String toHHmm(int m){
        int h = m / 60, mm = m % 60;
        return String.format("%02d:%02d", h, mm);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] t = new int[n];
        for(int i=0;i<n;i++) t[i] = toMin(br.readLine());
        int q = toMin(br.readLine());
        int lo = 0, hi = n - 1, ans = -1;
        while(lo <= hi){
            int mid = (lo + hi) >>> 1;
            if(t[mid] > q){
                ans = mid;
                hi = mid - 1;
            } else lo = mid + 1;
        }
        if(ans == -1) System.out.println("No bike");
        else System.out.println(toHHmm(t[ans]));
    }
}
