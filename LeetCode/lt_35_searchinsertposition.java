public class lt_35_searchinsertposition {

    // 主方法：找出 target 的索引或插入位置
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        // 二分搜尋
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到了
            }
            else if (nums[mid] < target) {
                left = mid + 1; // 向右找
            }
            else {
                right = mid - 1; // 向左找
            }
        }

        return left; // 沒找到，left 是插入位置
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_35_searchinsertposition solver = new lt_35_searchinsertposition();

        System.out.println(solver.searchInsert(new int[]{1, 3, 5, 6}, 5)); // ➜ 2
        System.out.println(solver.searchInsert(new int[]{1, 3, 5, 6}, 2)); // ➜ 1
        System.out.println(solver.searchInsert(new int[]{1, 3, 5, 6}, 7)); // ➜ 4
        System.out.println(solver.searchInsert(new int[]{1, 3, 5, 6}, 0)); // ➜ 0
    }
}

/*
📘 解題說明：
1. 題目要求：若 target 存在，回傳索引；若不存在，回傳應插入的位置。
2. 使用 Binary Search：
   - 當找到就回傳 mid。
   - 若找不到，left 會停在第一個比 target 大的索引，也就是插入點。
3. 避免寫成線性迴圈（如 for-loop 掃描），會違反 O(log n)。
*/
