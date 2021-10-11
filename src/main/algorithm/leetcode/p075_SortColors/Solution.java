package algorithm.leetcode.p075_SortColors;

import static org.testng.Assert.*;

import org.testng.annotations.Test;


// 262 array의 dutch national flag problem과 동일
public class Solution {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public int[] sortColors(int[] nums, int pivot) {
        int less = 0, bigger = nums.length - 1;
        for (int p = 0; p <= bigger; ) {
            if (nums[p] < pivot) {
                swap(nums, less++, p++);
            } else if (nums[p] > pivot) {
                swap(nums, p, bigger--);
            } else {
                p++;
            }
        }
        return nums;

/*      // simple solution
        int r = 0, w = 0, b = 0;
        for (int n : nums) {
            switch (n) {
                case 0:
                    r++;
                    break;
                case 1:
                    w++;
                    break;
                case 2:
                    b++;
                    break;
            }
        }
        for (int j = 0; j < r; j++) nums[j] = 0;
        for (int j = r; j < r + w; j++) nums[j] = 1;
        for (int j = r + w; j < r + w + b; j++) nums[j] = 2;

        return nums;*/
    }

    @Test
    public void test() {
        assertEquals(sortColors(new int[]{0, 1, 2, 0, 2, 1, 1}, 0), new int[]{0, 0, 2, 2, 1, 1, 1});
        assertEquals(sortColors(new int[]{0, 1, 2, 0, 2, 1, 1}, 2), new int[]{0, 1, 0, 1, 1, 2, 2});
    }
}
