package test;

/**
 * 编写一个函数，计算给定字符串中第一个不重复字符的索引。如果字符串中没有不重复的字符，则返回 -1。
 * public class Solution {
 * public static int firstUniqueCharIndex(String s) {
 * // 在此处实现你的代码
 * return -1;
 * }
 * }
 * <p>
 * 输入
 * 字符串 s (1 <= s.length() <= $10^5$)：包含小写英文字母的字符串。
 * 输出
 * 返回第一个不重复字符的索引，如果没有不重复字符则返回 -1。
 * 示例
 * public class Main {
 * public static void main(String[] args) {
 * assert Solution.firstUniqueCharIndex("xiaomicloud") == 0;
 * // 解释: 'x' 是第一个不重复字符，索引为 0
 * <p>
 * assert Solution.firstUniqueCharIndex("lovemicloud") == 2;
 * // 解释: 'v' 是第一个不重复字符，索引为 2
 * <p>
 * assert Solution.firstUniqueCharIndex("aabb") == -1;
 * }
 * }
 * <p>
 * 提示
 * 字符串中只包含小写英文字母。
 */

public class Main2 {

    public static void main(String[] args) {
        System.out.println(firstUniqueCharIndex("xiaomicloud") == 0);
        System.out.println(firstUniqueCharIndex("lovemicloud") == 2);
        System.out.println(firstUniqueCharIndex("aabb") == -1);
    }

    public static int firstUniqueCharIndex(String s) {
        char[] charArray = s.toCharArray();
        for (int i = 0; i< charArray.length; i++) {
            int ret1 = s.indexOf(charArray[i]);
            int ret2 = s.lastIndexOf(charArray[i]);
            // 如果位置相同，并且有值，说明只有一个元素
            if (ret1 == ret2) {
                return ret1;
            }  else {
                continue;
            }
        }
        return -1;
    }
}

