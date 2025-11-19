package kexin.letcode.数组;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{2,7,11,15};
        int[] twoSum = twoSum2(nums, 9);
        for (int i : twoSum) {
            System.out.print(i + ",");
        }
    }
    public static int[] twoSum(int[] nums, int target) {
        for(int i = 0; i <nums.length-1; i++) {
            for(int j = i + 1; j < nums.length; j ++) {
                if(nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }


    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = target - num;
            if (numsMap.containsKey(complement)) {
                return new int[]{i, numsMap.get(complement)};
            }
            // 关键语句，如果不包含，则放进map，遍历到后面的元素，
            // target-当前元素 存在与map中，则表示这两个数是匹配的
            numsMap.put(num, i);
        }
        return new int[2];
    }
}
