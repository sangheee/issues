package algorithm.leetcode.p123_BestTimeToBuyAndSellStock3;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class Solution {
	public int maxProfit(int[] prices) {
		int min = Integer.MAX_VALUE;
		int[] maxProfit1 = new int[prices.length];
		for (int i = 0, maxProfit = 0; i < prices.length; i++) {
			min = Math.min(min, prices[i]);
			maxProfit = Math.max(maxProfit, prices[i] - min);
			maxProfit1[i] = maxProfit;
		}

		int max = Integer.MIN_VALUE;
		int[] maxProfit2 = new int[prices.length];
		for (int j = prices.length - 1, maxProfit = 0; j >= 0; j--) {
			max = Math.max(max, prices[j]);
			maxProfit = Math.max(maxProfit, max - prices[j]);
			maxProfit2[j] = maxProfit;
		}

		int maxProfit = 0;
		for (int i = 0; i < prices.length; i++) {
			maxProfit = Math.max(maxProfit, maxProfit1[i] + maxProfit2[i]);
		}
		return maxProfit;
	}

	@Test
	public void test() {
		assertEquals(maxProfit(new int[] {1, 2, 3, 4, 5}), 4);
		assertEquals(maxProfit(new int[] {7, 6, 4, 3, 1}), 0);
		assertEquals(maxProfit(new int[] {3, 3, 5, 0, 0, 3, 1, 4}), 6);
		assertEquals(maxProfit(new int[] {1, 1, 5, 3, 6, 4, 7}), 8);
	}
}
