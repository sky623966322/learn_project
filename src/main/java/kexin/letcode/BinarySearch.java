package kexin.letcode;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 9;
        int index = binarySearch(nums, target);
        System.out.println(index);
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0, rigth = nums.length - 1;
        while(left <= rigth) {
            int mid = left + (rigth - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                rigth = mid - 1;
            } else if (target == nums[mid]) {
                return mid;
            }
        }
        return -1;
    }

}
