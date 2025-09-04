import java.util.Arrays;

public class lt_31_nextpermutation {

    // 主方法：就地修改陣列為下一個字典序排列
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // 1️⃣ 從後往前找到第一個遞增的位置 i（即 nums[i] < nums[i + 1]）
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 2️⃣ 如果找到了這樣的 i，找右側比 nums[i] 大的最小值 nums[j]
        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j); // 交換 i 與 j
        }

        // 3️⃣ 將 i+1 之後的元素反轉（由大到小 → 小到大）
        reverse(nums, i + 1, n - 1);
    }

    // 工具方法：交換兩元素
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 工具方法：反轉區間 [start, end]
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_31_nextpermutation solver = new lt_31_nextpermutation();

        int[] nums1 = {1, 2, 3};
        solver.nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1)); // ➜ [1, 3, 2]

        int[] nums2 = {3, 2, 1};
        solver.nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2)); // ➜ [1, 2, 3]

        int[] nums3 = {1, 1, 5};
        solver.nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3)); // ➜ [1, 5, 1]

        int[] nums4 = {1, 5, 1};
        solver.nextPermutation(nums4);
        System.out.println(Arrays.toString(nums4)); // ➜ [5, 1, 1]
    }
}

/*
📘 解題說明：
1. 題目要求取得「下一個排列」的字典序，並就地改變原陣列（不使用額外記憶體）。
2. 若為最大排列（如 [3,2,1]），則反轉成最小排列（[1,2,3]）。
3. 核心步驟：
   - 找到第一個 nums[i] < nums[i+1]
   - 找到比 nums[i] 大的最小值 nums[j] 並交換
   - 反轉 i+1 到末尾，讓後半段為最小升序
*/
