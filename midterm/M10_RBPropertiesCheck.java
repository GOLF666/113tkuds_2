import java.io.*;
import java.util.*;

public class M10_RBPropertiesCheck {
    static int n;
    static long[] val;
    static char[] col; // 'B' or 'R'; for val=-1 treat as black

    static boolean isNull(int i){ return i>=n || val[i]==-1; }
    static boolean isRed(int i){ return !isNull(i) && col[i]=='R'; }
    static boolean isBlack(int i){ return isNull(i) || col[i]=='B'; }

    // 返回黑高；若不一致回傳負值（-1）
    static int blackHeight(int i){
        if(isNull(i)) return 1; // NIL 作為黑節點高度 1
        int l = 2*i+1, r = 2*i+2;
        int bl = blackHeight(l); if(bl<0) return -1;
        int br = blackHeight(r); if(br<0) return -1;
        if(bl != br) return -1;
        return bl + (col[i]=='B' ? 1 : 0);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        val = new long[n];
        col = new char[n];

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        for(int i=0;i<n;i++){
            long v = Long.parseLong(st.nextToken());
            char c = st.hasMoreTokens() ? st.nextToken().charAt(0) : 'B';
            val[i] = v;
            col[i] = (v==-1 ? 'B' : c);
        }

        // 1) 根為黑
        if(n>0 && !isNull(0) && col[0] != 'B'){
            System.out.println("RootNotBlack");
            return;
        }

        // 2) 不得紅紅相鄰（第一個違規 index）
        for(int i=0;i<n;i++){
            if(isRed(i)){
                int l = 2*i+1, r = 2*i+2;
                if(isRed(l) || isRed(r)){
                    System.out.println("RedRedViolation at index " + i);
                    return;
                }
            }
        }

        // 3) 黑高一致
        int bh = blackHeight(0);
        if(bh < 0){
            System.out.println("BlackHeightMismatch");
        }else{
            System.out.println("RB Valid");
        }
    }
}
