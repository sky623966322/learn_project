package kexin.letcode;

public class RotateArray {

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        rotate(nums, 3);
        System.out.println(nums);
    }

    public static void rotate(int[] nums, int k) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int newIndex = (i + k) % nums.length;
            result[newIndex] = nums[i];
        }
        System.arraycopy(result, 0, nums, 0, nums.length);
    }

}
