package algorithm.leetcode.p122_BestTimeToBuyAndSellStock2;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class Solution {
	public int maxProfit(int[] prices) {
		if (prices.length < 2)
			return 0;

		int maxProfit = 0;
		for (int i = 1; i < prices.length; i++) {
			maxProfit = Math.max(maxProfit, maxProfit + prices[i] - prices[i - 1]);
		}

		return maxProfit;
	}

	@Test
	public void test() {
		assertEquals(maxProfit(new int[] {1, 2, 3, 4, 5}), 4);
		assertEquals(maxProfit(new int[] {7, 6, 4, 3, 1}), 0);
		assertEquals(maxProfit(new int[] {7, 1, 5, 3, 6, 4}), 7);
	}
}
