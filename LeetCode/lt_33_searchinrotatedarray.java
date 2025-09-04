public class lt_33_searchinrotatedarray {

    // 主方法：在旋轉排序陣列中找 target 的索引
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 二分搜尋
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 找到了
            if (nums[mid] == target) return mid;

            // 左半邊為遞增區間
            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target 在左半邊
                } else {
                    left = mid + 1; // target 在右半邊
                }
            }
            // 右半邊為遞增區間
            else {
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // target 在右半邊
                } else {
                    right = mid - 1; // target 在左半邊
                }
            }
        }

        return -1; // 沒找到
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_33_searchinrotatedarray solution = new lt_33_searchinrotatedarray();

        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(nums1, 0)); // ➜ 4

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(solution.search(nums2, 3)); // ➜ -1

        int[] nums3 = {1};
        System.out.println(solution.search(nums3, 0)); // ➜ -1

        int[] nums4 = {6,7,8,1,2,3,4,5};
        System.out.println(solution.search(nums4, 8)); // ➜ 2
    }
}

/*
📘 解題說明：
1. 題目保證陣列是升序陣列的旋轉版本（無重複數字），因此其中一側一定是有序的。
2. 使用 binary search，每次都檢查哪一邊是正常的遞增子區段。
3. 根據 target 是否落在該區段，調整左右指標（left / right）。
4. 時間複雜度 O(log n)，符合題目要求。
*/
