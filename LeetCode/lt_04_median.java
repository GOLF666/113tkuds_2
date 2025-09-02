package LeetCode;

import java.util.Arrays;

public class lt_04_median {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int i = 0, j = 0, k = 0;

        // 合併兩個排序陣列
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                merged[k++] = nums1[i++];
            } else {
                merged[k++] = nums2[j++];
            }
        }

        // 把剩下的加入 merged
        while (i < nums1.length) {
            merged[k++] = nums1[i++];
        }

        while (j < nums2.length) {
            merged[k++] = nums2[j++];
        }

        // 計算中位數
        int n = merged.length;
        if (n % 2 == 1) {
            return merged[n / 2];
        } else {
            return (merged[n / 2 - 1] + merged[n / 2]) / 2.0;
        }
    }

    // ✅ 測試用 main 方法
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Median is: " + findMedianSortedArrays(nums1, nums2)); // 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Median is: " + findMedianSortedArrays(nums3, nums4)); // 2.5
    }
}

/*
解題思路：
1. 題目要求找兩個排序陣列合併後的中位數。
2. 先使用雙指標合併兩陣列成一個新的排序陣列 merged。
3. 若 merged 長度為奇數，回傳中間元素。
4. 若長度為偶數，回傳中間兩數平均。
5. 時間複雜度為 O(m + n)，空間複雜度也為 O(m + n)。
⚠️ 若要達到 O(log(min(m,n))) 時間，需使用二分搜尋（進階解法）。
*/