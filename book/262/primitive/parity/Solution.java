package primitive.parity;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class Solution {
	public short parityWithShift(long x) {
		short parity = 0;
		while (x != 0) {
			parity ^= (x & 1);
			x >>>= 1;
		}
		return parity;
	}

	public short parityRemoveLowestBit(long x) {
		short parity = 0;
		// 가장 낮은 비트를 순차적으로 지워서 0을 만든다.
		// 시간복잡도 최악의 경우 O(n)이지만, 평균과 최선의 경우의 성능을 개선시킨다.
		while (x != 0) {
			parity ^= 1;
			x &= (x - 1);
		}
		return parity;
	}

	private static final short[] precomputedParity = new short[1 << 16];

	// initialize precomputedParity cache
	static {
		precomputedParity[0] = 0;
		precomputedParity[1] = 1;
		precomputedParity[2] = 1;
		precomputedParity[3] = 0;

		for (int i = 4; i < (1 << 16); i++) {
			precomputedParity[i] = (short)(precomputedParity[(i >>> (3 * 2)) & 3]
				^ precomputedParity[(i >>> (2 * 2)) & 3]
				^ precomputedParity[(i >>> (2)) & 3]
				^ precomputedParity[i & 3]);
		}
	}

	// 시간복잡도는 전체 비트 수가 n이고 cache 비트 수가 L일 때  O(n/L)
	public short parityWithCache(long x) {
		int worldSize = 16;
		int bitMask = 0xFFFF;
		return (short)(precomputedParity[(int)((x >>> (3 * worldSize)) & bitMask)]
			^ precomputedParity[(int)((x >>> (2 * worldSize)) & bitMask)]
			^ precomputedParity[(int)(x >>> worldSize) & bitMask]
			^ precomputedParity[(int)x & bitMask]);
	}

	// 시간복잡도는 O(long n)
	public short parityShiftAndXorAndCache(long x) {
		x ^= x >>> 32;
		x ^= x >>> 16;
		return precomputedParity[(int)x];
	}

	@Test
	public void test() {
		long testParity = 0xFFFFL;
		assertEquals(parityWithShift(testParity), 0);
		assertEquals(parityRemoveLowestBit(testParity), 0);
		assertEquals(parityWithCache(testParity), 0);
		assertEquals(parityShiftAndXorAndCache(testParity), 0);
	}
}
