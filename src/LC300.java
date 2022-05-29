import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC300 {
    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * <p>
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     */

    private Map<Integer, List<Integer>> resultMap;

    private int[] dp;

    public LC300() {
        this.resultMap = new HashMap<>();
    }

    public int solution(int[] nums) {
        int len = nums.length;
        this.dp = new int[len];
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int each = helper(nums, i, dp);
            if (each >= result) result = each;
        }
        System.out.println(this.resultMap);
        return result;
    }

    public int helper(int[] nums, int endOfIndex, int[] dp) {
        List<Integer> eachList = new ArrayList<>();
        int result = 1;
        // 当以第一个数为结尾时，最长子序列就是其本身
        if (endOfIndex == 0) {
            dp[endOfIndex] = result;
            eachList.add(nums[endOfIndex]);
        } else {
            for (int i = 0; i < endOfIndex; i++) {
                if (nums[i] < nums[endOfIndex]) {
                    // 当且仅当nums[i] < endOfIndex时，才会构成递增子序列
                    result = Math.max(result, dp[i] + 1);
                    dp[endOfIndex] = result;
                    if (eachList.size() < this.resultMap.get(i).size() + 1) {
                        eachList = new ArrayList<>(this.resultMap.get(i));
                        eachList.add(nums[endOfIndex]);
                    }
                }
            }
        }
        this.resultMap.put(endOfIndex, eachList);
        return result;
    }

    @Test
    public void test() {
        int[] test = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(solution(test));
    }
}
