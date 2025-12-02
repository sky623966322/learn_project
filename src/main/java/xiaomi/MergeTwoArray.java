package xiaomi;

/**
 * 两个无序整型数组合并成一个有序数组，并去重，
 * <p>
 * 要求：1、硬编码 2、不能使用集合和集合方法
 */
public class MergeTwoArray {
    public static void main(String[] args) {
        int[] num1 = new int[]{9,7,3};
        int[] num2 = new int[]{8,5,3};
        int[] sortUnique = mergeAndSortUnique(num1, num2);
        System.out.println(sortUnique);
    }

    public static int[] mergeAndSortUnique(int[] nums1, int[] nums2) {
        /**
         * 1、先将nums1、nums2合并
         * 2、对合并后的数组去重
         * 3、对去重后的数组排序
         */
        // 合并
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] mergeArray = new int[length1 + length2];
        for (int i = 0; i < length1; i++) {
            mergeArray[i] = nums1[i];
        }
        for (int i = 0; i < length2; i++) {
            mergeArray[length1 + i] = nums2[i];
        }
        // 去重
        int length = mergeArray.length;
        int[] tempArray = new int[length];
        int uniqueCount = 0;
        for (int i = 0; i < length; i++) {
            boolean isDuplicate = false;
            for (int j = i + 1; j < length; j++) {
                if (mergeArray[i] == mergeArray[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            // 如果i不是重复的,则添加到tempArray
            if(!isDuplicate) {
                tempArray[uniqueCount] = mergeArray[i];
                uniqueCount++;
            }
        }
        // 根据tempArray创建去重后的数组
        int[] uniqueArray = new int[uniqueCount];
        for (int i = 0; i < uniqueCount; i++) {
            uniqueArray[i] = tempArray[i];
        }
        // 对uniqueArray排序
        /**
         * [9,7,3,5,8]
         *
         * [7,3,5,8,9]
         */
        for (int i = 0; i < uniqueArray.length; i++) {
            // 最后一个数字不需要往后比较，所有-1
            for (int j = 0; j < uniqueArray.length - i - 1; j++) {
                // 如果前面数大于后面数，则交换位置
                if (uniqueArray[j] > uniqueArray[j + 1]) {
                   int temp = uniqueArray[j];
                   uniqueArray[j] = uniqueArray[j + 1];
                   uniqueArray[j + 1] = temp;
                }
            }
        }
        return uniqueArray;
    }

}
