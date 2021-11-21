package algorithm.leetcode.p127_WordLadder;

import org.testng.annotations.Test;

import java.util.*;

import static org.testng.Assert.assertEquals;

public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;

        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        int level = 1;


        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String word = q.poll();

                if (word.equals(endWord))
                    return level;

                // create mod str from word
                for (int j = 0; j < word.length(); j++) {
                    String startStr = j == 0 ? "" : word.substring(0, j);
                    String endStr = j + 1 == word.length() ? "" : word.substring(j + 1);
                    for (char c = 'a'; c <= 'z'; c++) {
                        String modString = startStr + c + endStr;
                        if (wordSet.remove(modString))
                            q.add(modString);
                    }
                }
            }
            level++;
        }
        return 0;
    }

    @Test
    public void test() {
        assertEquals(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")), 5);
        assertEquals(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log")), 0);
    }
}
