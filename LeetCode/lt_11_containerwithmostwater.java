
class lt_11_containerwithmostwater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        // 使用雙指標從兩端開始，逐步縮小範圍
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            maxArea = Math.max(maxArea, h * w);

            // 移動較短的一邊，以期望找到更高的邊
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        lt_11_containerwithmostwater solution = new lt_11_containerwithmostwater();
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max area: " + solution.maxArea(height)); // Output: 49
    }
}

/*
解題思路：
1. 使用雙指標從左右兩端開始計算面積。
2. 每次選擇較短的邊移動，希望找到更高的高度。
3. 記錄過程中的最大面積，直到指標交錯。
4. 時間複雜度為 O(n)，空間複雜度為 O(1)。
*/