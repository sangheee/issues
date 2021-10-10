package algorithm.leetcode.p905_SortArrayByParity;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class Solution {
	public int[] sortArrayByParity(int[] nums) {
		for (int i = 0, j = 0; j < nums.length; j++) {
			if (nums[j] % 2 == 0) {
				int tmp = nums[j];
				nums[j] = nums[i];
				nums[i++] = tmp;
			}
		}

		return nums;
	}

	@Test
	public void test() {
		assertEquals(sortArrayByParity(new int[] {3, 2, 1, 4}), new int[] {4, 2, 1, 3});
		assertEquals(sortArrayByParity(new int[] {0}), new int[] {0});
	}
}
