import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        long qty;
        int idx; // 輸入順序（若要用此穩定性也可）
        Item(String n, long q, int i){ name=n; qty=q; idx=i; }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Item> pq = new PriorityQueue<>( (x,y)->{
            if (x.qty != y.qty) return Long.compare(x.qty, y.qty);   // Min-Heap by qty
            return x.name.compareTo(y.name);                         // 同量時字典序小者視為「較小」
        });
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            long qty = Long.parseLong(st.nextToken());
            pq.offer(new Item(name, qty, i));
            if(pq.size() > K) pq.poll();
        }
        List<Item> res = new ArrayList<>();
        while(!pq.isEmpty()) res.add(pq.poll());
        // 輸出：高到低；若數值相同，依字典序升冪
        res.sort((a,b)->{
            if (a.qty != b.qty) return Long.compare(b.qty, a.qty);
            return a.name.compareTo(b.name);
        });
        StringBuilder sb = new StringBuilder();
        for(Item it: res){
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        }
        System.out.print(sb.toString());
    }
}

/*
 * Time Complexity: O(n log K + K log K)
 * 說明：逐筆維護大小為 K 的最小堆，插入/彈出為 O(log K)，總計 O(n log K)；最後對 K 筆結果排序 O(K log K)。
 *      由於 K ≪ n，整體主導項為 O(n log K)。
 */
