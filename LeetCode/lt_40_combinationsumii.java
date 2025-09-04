import java.util.*;

public class lt_40_combinationsumii {

    // 主方法：回傳所有符合條件的組合
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // ✅ 先排序，方便去重
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    // 回溯主體
    private void backtrack(int[] candidates, int target, int start, List<Integer> combination, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination)); // 找到合法組合
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];

            // ❌ 若重複使用相同數字，略過（同一層內）
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            // 剪枝：若 num > target，後面更大，提早結束
            if (num > target) break;

            // 選擇
            combination.add(num);

            // 遞迴：從下一個數字開始（不能重複）
            backtrack(candidates, target - num, i + 1, combination, result);

            // 回溯
            combination.remove(combination.size() - 1);
        }
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_40_combinationsumii solver = new lt_40_combinationsumii();

        int[] nums1 = {10, 1, 2, 7, 6, 1, 5};
        System.out.println(solver.combinationSum2(nums1, 8));
        // ➜ [[1,1,6], [1,2,5], [1,7], [2,6]]

        int[] nums2 = {2, 5, 2, 1, 2};
        System.out.println(solver.combinationSum2(nums2, 5));
        // ➜ [[1,2,2], [5]]
    }
}

/*
📘 解題說明：
1. 題目要求組合總和為 target，每個數字只能用一次（雖然 candidates 中可能有重複值）。
2. 關鍵：
   - 先排序：重複值會相鄰
   - 在同一層遞迴中，如果前一個數字和現在一樣，代表會產生重複組合，要跳過（i > start && nums[i] == nums[i-1]）
   - 每次遞迴傳 i+1，保證每個數字只用一次
*/
