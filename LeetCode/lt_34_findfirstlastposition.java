import java.util.Arrays;

public class lt_34_findfirstlastposition {

    // 主方法：回傳 target 的起始與結束索引
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);   // 找起始位置
        int last = findBound(nums, target, false);   // 找結束位置
        return new int[] { first, last };
    }

    // Binary Search Helper：根據 isFirst 決定找第一個還是最後一個出現位置
    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0;
        int right = nums.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                result = mid;

                // 如果要找第一個出現的，就往左邊繼續找
                if (isFirst) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            else if (nums[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return result;
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_34_findfirstlastposition solver = new lt_34_findfirstlastposition();

        int[] result1 = solver.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8);
        System.out.println(Arrays.toString(result1)); // ➜ [3, 4]

        int[] result2 = solver.searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6);
        System.out.println(Arrays.toString(result2)); // ➜ [-1, -1]

        int[] result3 = solver.searchRange(new int[] {}, 0);
        System.out.println(Arrays.toString(result3)); // ➜ [-1, -1]

        int[] result4 = solver.searchRange(new int[] {2, 2, 2, 2, 2}, 2);
        System.out.println(Arrays.toString(result4)); // ➜ [0, 4]
    }
}

/*
📘 解題說明：
1. 題目要求找到 target 的開始與結束位置（如出現多次），若不存在則回 [-1, -1]。
2. 使用二分搜尋法找：
   - 第一次出現的位置（左邊界）
   - 最後一次出現的位置（右邊界）
3. 時間複雜度 O(log n)，符合題目要求。
4. 輔助函式 findBound 傳入參數 isFirst：
   - isFirst = true 時找左邊界
   - isFirst = false 時找右邊界
*/
