package kexin.letcode;

public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,3,12};
        moveZeroes(nums);
        System.out.println(nums);
    }

    public static void moveZeroes(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] == 0) {
                for (int i = left + 1; i <= right; i++) {
                    nums[i - 1] = nums[i];
                }
                nums[right] = 0;
                right--;
            } else {
                left ++;
            }
        }
    }
}
