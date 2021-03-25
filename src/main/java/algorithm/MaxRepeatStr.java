package algorithm;

import org.junit.Test;

import java.util.Arrays;

/**
 * 最长重复子串
 *
 * 例如
 * "ababc"  最长重复字符子串，长度为4
 */
public class MaxRepeatStr {

    @Test
    public void testSolve() {
        System.out.println(solve("abcdeabcdeabc"));
    }

    public int solve(String input) {
        //二分思想，如果存在重复子串m的话 2 * m < = a.length
        //逐步递减 m 直到0
        if(input != null || input.length() == 0) return 0;

        char[] charArray = input.toCharArray();
        int maxLen = charArray.length / 2;
        while (maxLen > 0) {
            int start = 0;
            while (start <= charArray.length - maxLen * 2) {
                //判断数组是否相同，相同直接返回，否则索引左移动
                if (check(charArray, start, maxLen)) {
                    return maxLen * 2;
                }
                start++;
            }
            //减少长度，再次寻找
            maxLen--;
        }
        return maxLen;
    }

    public boolean check(char[] chars, int start, int len) {
        for (int i = start; i < start + len; ++i) {
            if (chars[i] != chars[i + len])
                return false;
        }
        return true;
    }
}
