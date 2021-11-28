package algorithm.leetcode.p130_SurroundedRegions;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import static org.testng.Assert.assertTrue;

public class Solution {
    private static final int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    // dfs인데 [0,0]에서 시작해 [n,m]을 순차적으로 순회 time complexity 최대 O(4*n*m) -> O(n*m)
    // n*m visited array, stack, 변환할 point를 저장하는 list까지 space complexity O(n*m)
    public void solve1(char[][] board) {
        boolean[][] visited = new boolean[board.length][];
        for (int i = 0; i < board.length; i++) {
            visited[i] = new boolean[board[i].length];
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (visited[i][j])
                    continue;

                if (board[i][j] == 'O') {
                    Stack<int[]> stack = new Stack<>();
                    List<int[]> list = new LinkedList<>();
                    boolean notSurrounded = false;
                    int[] point = new int[]{i, j};
                    stack.add(point);
                    list.add(point);

                    while (!stack.isEmpty()) {
                        int[] p = stack.pop();
                        visited[p[0]][p[1]] = true;

                        for (int[] d : direction) {
                            int newX = p[0] + d[0], newY = p[1] + d[1];

                            if ((newX >= 0 && newX < board.length)
                                    && (newY >= 0 && newY < board[p[0]].length)) {
                                if (visited[newX][newY])
                                    continue;

                                if (board[newX][newY] == 'O') {
                                    point = new int[]{newX, newY};
                                    stack.add(point);
                                    list.add(point);
                                } else
                                    visited[newX][newY] = true;
                            } else {
                                notSurrounded = true;
                            }
                        }
                    }

                    if (!notSurrounded) {
                        for (int[] p : list) {
                            board[p[0]][p[1]] = 'X';
                        }
                    }
                } else {
                    visited[i][j] = true;
                }
            }
        }

    }

    // 테두리 먼저 순회하는 dfs
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        // top row
        for (int i = 0; i < m; i++)
            helper(board, n, m, 0, i);

        // bottom row
        for (int i = 0; i < m; i++)
            helper(board, n, m, n - 1, i);

        // left col
        for (int i = 0; i < n; i++)
            helper(board, n, m, i, 0);

        // right col
        for (int i = 0; i < n; i++)
            helper(board, n, m, i, m - 1);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '1')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }

    }

    private void helper(char[][] board, int n, int m, int r, int c) {
        if (board[r][c] != 'O')
            return;

        board[r][c] = '1';

        for (int[] d : direction) {
            if ((r + d[0] >= 0 && r + d[0] < n) && (c + d[1] >= 0 && c + d[1] < m)) {
                helper(board, n, m, r + d[0], c + d[1]);
            }
        }
    }


    @Test
    public void test() {
        char[][] chars = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(chars);
        assertTrue(Arrays.deepEquals(chars, new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'X', 'X', 'X'}, {'X', 'O', 'X', 'X'}}));

        chars = new char[][]{{'X'}};
        solve(chars);
        assertTrue(Arrays.deepEquals(chars, new char[][]{{'X'}}));
    }
}
