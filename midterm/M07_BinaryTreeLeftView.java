import java.io.*;
import java.util.*;

public class M07_BinaryTreeLeftView {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) a[i] = Integer.parseInt(st.nextToken());

        if(n==0 || a[0]==-1){
            System.out.println("LeftView:");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("LeftView:");
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        while(!q.isEmpty()){
            int sz = q.size();
            boolean leftPrinted = false;
            for(int k=0;k<sz;k++){
                int idx = q.poll();
                if(idx>=n || a[idx]==-1) continue;
                if(!leftPrinted){
                    sb.append(' ').append(a[idx]);
                    leftPrinted = true;
                }
                int l = idx*2+1, r = idx*2+2;
                if(l<n && a[l]!=-1) q.offer(l);
                if(r<n && a[r]!=-1) q.offer(r);
            }
        }
        System.out.println(sb.toString());
    }
}
