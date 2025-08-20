import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {
    static class Node {
        int time, listId, idx;
        Node(int t,int l,int i){ time=t; listId=l; idx=i; }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine().trim());
        List<int[]> lists = new ArrayList<>();
        for(int i=0;i<K;i++){
            int len = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[len];
            if(len>0){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<len;j++) arr[j] = Integer.parseInt(st.nextToken());
            }else{
                // 若 len==0，讀不到該行則不處理
            }
            lists.add(arr);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->Integer.compare(a.time,b.time));
        for(int i=0;i<K;i++){
            if(lists.get(i).length>0){
                pq.offer(new Node(lists.get(i)[0], i, 0));
            }
        }
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(!first) sb.append(' ');
            first = false;
            sb.append(cur.time);
            int ni = cur.idx + 1;
            if(ni < lists.get(cur.listId).length){
                pq.offer(new Node(lists.get(cur.listId)[ni], cur.listId, ni));
            }
        }
        System.out.println(sb.toString());
    }
}
