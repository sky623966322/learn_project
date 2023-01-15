package algorithm;

import java.util.HashMap;
import java.util.Map;

public class LongestAwesome {
    public int longestAwesome(String s) {
        /**
         * 思路：
         * 1. 变量s字符串的子集；
         * 2. 如果子集为偶数个元素，则其中出现的每个元素也为 2*n ，则为回文
         */
        int maxLongth = 1;
        char[] charArr = s.toCharArray();
        int length = charArr.length;
        for (int i = 0; i < length; i++) {
            Map<String, Integer> map = new HashMap<>();
            for (int j = i; j < length; j++) {
                char item = charArr[j];
                Integer value = map.get(item) == null ? 0 : map.get(item);
                map.put(item + "", value + 1);
            }
            
            // 如果map.entrySize 为偶数，则value都为2*n, 记录size的和
            // 如果map.entrySize为奇数，则只有一个value为奇数，其他value都为偶数
            int entrySize = map.entrySet().size();
            if (isOdd(entrySize)) {
                boolean isOdd = true;
                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    isOdd = isOdd && isOdd(entry.getValue());
                }
                if (isOdd) {

                }
            } else {

            }
        }
        return 0;
    }

    private boolean isOdd(int size){
        if (size % 2 == 0) {
            return true;
        }
        return false;
    }
}
