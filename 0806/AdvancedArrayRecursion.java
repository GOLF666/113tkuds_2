import java.util.*;

public class AdvancedArrayRecursion {

    // 快速排序
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pi = partition(arr, left, right);
            quickSort(arr, left, pi - 1);
            quickSort(arr, pi + 1, right);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high], i = low - 1;
        for (int j = low; j < high; j++)
            if (arr[j] < pivot)
                swap(arr, ++i, j);
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }

    // 合併已排序陣列（遞迴）
    public static int[] merge(int[] a, int[] b) {
        if (a.length == 0) return b;
        if (b.length == 0) return a;

        if (a[0] < b[0])
            return concat(new int[]{a[0]}, merge(Arrays.copyOfRange(a, 1, a.length), b));
        else
            return concat(new int[]{b[0]}, merge(a, Arrays.copyOfRange(b, 1, b.length)));
    }

    private static int[] concat(int[] a, int[] b) {
        int[] result = Arrays.copyOf(a, a.length + b.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    // 尋找第k小元素
    public static int kthSmallest(int[] arr, int k) {
        int[] sorted = arr.clone();
        quickSort(sorted, 0, sorted.length - 1);
        return sorted[k - 1];
    }

    // 子序列總和為 target
    public static boolean hasSubSum(int[] arr, int target, int index) {
        if (target == 0) return true;
        if (index == arr.length) return false;
        return hasSubSum(arr, target - arr[index], index + 1) || hasSubSum(arr, target, index + 1);
    }

    public static void main(String[] args) {
        int[] a = {7, 2, 1, 6, 8};
        quickSort(a, 0, a.length - 1);
        System.out.println("QuickSort: " + Arrays.toString(a));

        int[] merged = merge(new int[]{1, 4, 7}, new int[]{2, 3, 6});
        System.out.println("Merged: " + Arrays.toString(merged));

        System.out.println("第3小元素: " + kthSmallest(new int[]{5, 2, 7, 1, 3}, 3));
        System.out.println("是否存在總和為9的子序列: " + hasSubSum(new int[]{3, 4, 5, 2}, 9, 0));
    }
}
