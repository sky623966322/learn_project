package kexin.letcode.数组;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Intersection {
    public static void main(String[] args) {
        // nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        int[] nums1 = new int[]{4,9,5};
        int[] nums2 = new int[]{9,4,9,8,4};
        int[] result = intersection2(nums1, nums2);
        for (int i : result) {
            System.out.printf(i+ ", ");
        }
    }
    public static int[] intersection(int[] nums1, int[] nums2) {
        // 先排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // nums1 = [4,5,9], nums2 = [4,4,8,9]

        int resultLength = nums1.length > nums2.length ? nums1.length : nums2.length;
        int[] result = new int[resultLength];

        int p1 = 0;
        int p2 = 0;
        int count = 0;
         while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                if (count == 0 || result[count - 1] != nums1[p1]) {
                    result[count] = nums1[p1];
                    count++;
                }
                p1++;
                p2++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            }
        }
        int[] result2 = new int[count];
        for (int i = 0; i < result2.length; i++) {
            result2[i] = result[i];
        }
        return result2;
    }

    public static int[] intersection2(int[] nums1, int[] nums2) {
        Set<Integer> result = new HashSet<>();
        if (nums1.length > nums2.length) {
            Set<Integer> set1 = new HashSet<>();
            for (int i : nums1) {
                set1.add(i);
            }
            for (int i = 0; i < nums2.length; i++) {
                if (set1.contains(nums2[i])) {
                    result.add(nums2[i]);
                }
            }
        } else {
            Set<Integer> set2 = new HashSet<>();
            for (int i : nums2) {
                set2.add(i);
            }
            for (int i = 0; i < nums1.length; i++) {
                if (set2.contains(nums1[i])) {
                    result.add(nums1[i]);
                }
            }
        }
        int[] result2 = new int[result.size()];
        int count = 0;
        for (Integer i : result) {
            result2[count++] = i;
        }
        return result2;
    }
}
