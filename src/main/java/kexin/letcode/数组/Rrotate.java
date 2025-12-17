package kexin.letcode.数组;

public class Rrotate {
    public static void main(String[] args) {
        int[] nums = new int[]{-1};
        rotate2(nums, 3);
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }

    public static void rotate(int[] nums, int k) {
        int[] dest = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dest[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < dest.length; i++) {
            nums[i] = dest[i];
        }
    }

    public static void rotate2(int[] nums, int k) {
        // [1, 2, 3, 4, 5, 6, 7]
        // [5, 6, 7, 1, 2, 3, 4]
        if (nums.length < k) {
            return;
        }
        swap(nums, 0, nums.length - 1);
        swap(nums, 0, k - 1);
        swap(nums, k, nums.length - 1);
    }

    public static void swap(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
