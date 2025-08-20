import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {
    static class P {
        int score;
        int idx; // 輸入順序
        P(int s, int i){ score=s; idx=i; }
    }
    // Max-Heap 比較（較大者：score 高；同分 idx 小者視為「較大」=> 先出）
    static boolean greater(P a, P b){
        if(a.score != b.score) return a.score > b.score;
        return a.idx < b.idx;
    }
    static void swap(P[] arr, int i, int j){ P t=arr[i]; arr[i]=arr[j]; arr[j]=t; }
    static void heapifyDown(P[] arr, int size, int i){
        while(true){
            int l=i*2+1, r=i*2+2, best=i;
            if(l<size && greater(arr[l], arr[best])) best=l;
            if(r<size && greater(arr[r], arr[best])) best=r;
            if(best==i) break;
            swap(arr, i, best);
            i = best;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        P[] a = new P[n];
        for(int i=0;i<n;i++) a[i] = new P(Integer.parseInt(st.nextToken()), i);

        // build max-heap
        for(int i=(n/2)-1;i>=0;i--) heapifyDown(a, n, i);
        // heapsort：最大者放到尾端，最後得到升序（因為最大的被丟到最後）
        int size = n;
        while(size > 1){
            swap(a, 0, size-1);
            size--;
            heapifyDown(a, size, 0);
        }
        // 輸出升冪之 score
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            if(i>0) sb.append(' ');
            sb.append(a[i].score);
        }
        System.out.println(sb.toString());
    }
}

/*
 * Time Complexity: O(n log n)
 * 說明：先 O(n) 建堆，再進行 n-1 次「取最大並下濾」操作，每次 O(log n)，總 O(n log n)。
 *      同分時以較小索引視為較大者放後面，確保輸出時同分之較小索引較晚被置換，最終升冪序仍符合 tie 規則。
 */
