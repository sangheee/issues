package algorithm.leetcode.p121_BestTimeToBuyAndSellStock;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0, min = Integer.MAX_VALUE;
        for (int p : prices) {
            min = min > p ? p : min;
            maxProfit = maxProfit > p - min ? maxProfit : p - min;
        }
        return maxProfit;
    }

    @Test
    public void test() {
        assertEquals(maxProfit(new int[]{7, 6, 4, 3, 1}), 0);
        assertEquals(maxProfit(new int[]{7, 1, 5, 3, 6, 4}), 5);
    }
}
