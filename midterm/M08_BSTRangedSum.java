import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {
    static int n;
    static long[] a;
    static long L, R;

    static long dfs(int i){
        if(i>=n || a[i]==-1) return 0;
        long val = a[i];
        long sum = 0;
        if(val >= L && val <= R) sum += val;
        // BST 剪枝
        if(val > L) sum += dfs(i*2+1);
        if(val < R) sum += dfs(i*2+2);
        return sum;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        a = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) a[i] = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        L = Long.parseLong(st.nextToken());
        R = Long.parseLong(st.nextToken());
        long ans = dfs(0);
        System.out.println("Sum: " + ans);
    }
}
