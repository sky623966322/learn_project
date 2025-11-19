package xiaomi;

/**
 * 两个无序整型数组合并成一个有序数组，并去重，
 *
 * 要求：1、硬编码 2、不能使用集合和集合方法
 */
public class MergeTwoArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 5, 3}; // 2， 3， 5
        int[] nums2 = new int[]{9, 2, 8}; // 2, 8, 9

    }

   public static int[] mergeAndSortUnique(int[] nums1, int[] nums2){
       /**
        * 1、先将nums1、nums2合并
        * 2、对合并后的数组去重
        * 3、对去重后的数组排序
        */
       // 合并
       int length1 = nums1.length;
       int length2 = nums2.length;
       int[] mergeArray = new int[]{length1 + length2};
       for (int i = 0; i < length1; i++) {
           mergeArray[i] = nums1[i];
       }
       for (int i = 0; i < length2; i++) {
           mergeArray[length1 + i] = nums2[i];
       }
       // 排序

        return mergeArray;
   }

    public static int[] merge(int[] nums1, int[] nums2) {
        int[] sort1 = sortAndUnique(nums1);
        int[] sort2 = sortAndUnique(nums2);

        int p1 = 0;
        int p2 = 0;
        int count = 0;
        int[] result = new int[sort1.length + sort2.length];
        while (p1 < sort1.length || p1 < sort2.length) {
            if (sort1[p1] <= sort2[p2]) {
                result[count] = sort1[p1];
                p1++;
            } else if (sort1[p1] > sort2[p2]) {
                result[count] = sort2[p2];
                p2++;
            }
            count ++;
        }
        return result;
    }

    public static int[] sortAndUnique(int[] nums) {
        int[] result = new int[nums.length];

    }

}
