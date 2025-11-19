package test;


/**
 * 给定一个无序的整数数组，找出最长连续序列的长度
 */

public class Main {

    public static void main(String[] args) {
        int[] intArr = new int[]{3, 1, 4, 5, 2, 5, 6, 7, 9};
        int count = 0;

        for (int i = 0; i < intArr.length; i++) {
            boolean tempRet = true;
            int tempCount = 1;
            for (int j = intArr.length - 1; j > i; j--) {
                tempRet &= (intArr[j - 1] + 1 == intArr[j]);
                if (tempRet) {
                    tempCount ++;
                } else {
                    continue;
                }
            }
            count = tempCount > count ? tempCount : count;
        }
        System.out.println(count);
    }

}
