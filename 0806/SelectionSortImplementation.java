public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        int comparisons = 0, swaps = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                swaps++;
            }
            System.out.println("第 " + (i + 1) + " 輪: " + java.util.Arrays.toString(arr));
        }

        System.out.println("比較次數: " + comparisons);
        System.out.println("交換次數: " + swaps);
    }
}
