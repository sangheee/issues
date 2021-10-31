package algorithm.leetcode.p150_ReversePolishNotation;

import static org.testng.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import org.testng.annotations.Test;

public class Solution {
	private static final Set<String> operatorSet = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

	private int operate(String operator, int v1, int v2) {
		switch (operator) {
			case "+":
				return v1 + v2;
			case "-":
				return v1 - v2;
			case "*":
				return v1 * v2;
			case "/":
				if (v2 != 0)
					return v1 / v2;
		}
		return 0;
	}

	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < tokens.length; i++) {
			String v = tokens[i];
			if (operatorSet.contains(v)) {
				int num1 = stack.pop();
				int num2 = stack.pop();
				stack.push(operate(v, num2, num1));
			} else {
				stack.push(Integer.valueOf(v));
			}
		}
		return stack.pop();
	}

	@Test
	public void test() {
		assertEquals(evalRPN(new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}), 22);
	}
}
