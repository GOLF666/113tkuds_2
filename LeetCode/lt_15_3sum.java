package LeetCode;

import java.util.*;

class lt_15_3sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序以利雙指標處理

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳過重複的 i 值，避免重複答案
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = nums.length - 1;

            // 雙指標尋找其餘兩數
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳過重複的 left/right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 需更大數字
                } else {
                    right--; // 需更小數字
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        lt_15_3sum solution = new lt_15_3sum();
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4})); // [[-1,-1,2], [-1,0,1]]
        System.out.println(solution.threeSum(new int[]{0, 0, 0}));             // [[0,0,0]]
        System.out.println(solution.threeSum(new int[]{0, 1, 1}));             // []
    }
}

/*
解題思路：
1. 將陣列排序，方便後續使用雙指標找兩數相加為 target。
2. 固定一個數 nums[i]，從 i+1 和結尾設置雙指標。
3. 若三數和為 0，加入結果並跳過重複元素。
4. 若和 < 0 則左指標右移，若和 > 0 則右指標左移。
5. 需注意跳過重複三元組，避免答案重複。
時間複雜度：O(n^2)，空間複雜度：O(1)（不計輸出）。
*/
