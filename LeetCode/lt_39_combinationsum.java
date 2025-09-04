import java.util.ArrayList;
import java.util.List;

public class lt_39_combinationsum {

    // 主方法：返回所有符合條件的組合
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    // 回溯方法
    private void backtrack(int[] candidates, int target, int start, List<Integer> combination, List<List<Integer>> result) {
        // ✅ 成功找到組合
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        // 遍歷從 start 開始的所有候選數字（允許重複用自己）
        for (int i = start; i < candidates.length; i++) {
            int num = candidates[i];

            // 若 num 超過 target，則剪枝
            if (num > target) continue;

            // 做選擇
            combination.add(num);

            // 因為可以重複使用當前數字，所以傳入 i（不是 i+1）
            backtrack(candidates, target - num, i, combination, result);

            // 撤銷選擇（回溯）
            combination.remove(combination.size() - 1);
        }
    }

    // 測試用 main 方法
    public static void main(String[] args) {
        lt_39_combinationsum solver = new lt_39_combinationsum();

        int[] candidates1 = {2, 3, 6, 7};
        System.out.println(solver.combinationSum(candidates1, 7));
        // ➜ [[2, 2, 3], [7]]

        int[] candidates2 = {2, 3, 5};
        System.out.println(solver.combinationSum(candidates2, 8));
        // ➜ [[2,2,2,2], [2,3,3], [3,5]]

        int[] candidates3 = {2};
        System.out.println(solver.combinationSum(candidates3, 1));
        // ➜ []
    }
}

/*
📘 解題說明：
1. 使用 Backtracking 找所有可行組合，條件是：
   - 數字總和等於 target
   - 同一數字可重複使用多次
2. 為了避免重複組合（[2,3,2] vs [2,2,3]），
   每次遞迴只從當前 index（i）開始，不重複選前面的數。
*/
