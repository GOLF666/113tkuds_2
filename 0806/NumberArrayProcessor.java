import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 3, 4, 4, 5};
        int[] arr2 = {2, 4, 6, 8};

        // 移除重複
        Set<Integer> unique = new LinkedHashSet<>();
        for (int num : arr1) unique.add(num);
        System.out.println("移除重複: " + unique);

        // 合併排序陣列
        int[] merged = mergeSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6});
        System.out.println("合併後: " + Arrays.toString(merged));

        // 出現頻率最高元素
        int mode = findMode(arr1);
        System.out.println("最常出現元素: " + mode);

        // 分割陣列
        splitArray(new int[]{1, 2, 3, 4, 5, 6});
    }

    static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length)
            result[k++] = (a[i] < b[j]) ? a[i++] : b[j++];
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }

    static int findMode(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : arr) freq.put(n, freq.getOrDefault(n, 0) + 1);
        return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    static void splitArray(int[] arr) {
        int total = Arrays.stream(arr).sum(), sum = 0;
        List<Integer> left = new ArrayList<>();
        for (int val : arr) {
            sum += val;
            left.add(val);
            if (sum >= total / 2) break;
        }
        System.out.println("分割結果: 左半部: " + left);
    }
}
