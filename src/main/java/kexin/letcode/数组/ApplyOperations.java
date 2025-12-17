package kexin.letcode.数组;

public class ApplyOperations {
    public static void main(String[] args) {
        int[] nums = new int[]{0,1};
        int[] result = applyOperations2(nums);
        for (int i : result) {
            System.out.print(i + ", ");
        }
    }

    public static int[] applyOperations3(int[] nums) {
        int p2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                nums[i] = 2 * nums[i];
                nums[i + 1] = 0;
            }
            if (nums[i] != 0) {
                int temp = nums[p2];
                nums[p2] = nums[i];
                nums[i] = temp;
                p2++;
            }
        }
        return nums;
    }

    public static int[] applyOperations2(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                nums[i - 1] = 2 * nums[i - 1];
                nums[i] = 0;
            }
        }
        moveZero(nums);
        return nums;
    }

    public static int[] applyOperations(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if ((i < nums.length - 1) && (nums[i] == nums[i+1])) {
                result[i] = 2 * nums[i];
                result[i + 1] = 0;
                nums[i] = 2 * nums[i];
                nums[i + 1] = 0;
            } else {
                result[i] = nums[i];
            }
        }
        int p2 = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                int temp = result[p2];
                result[p2] = result[i];
                result[i] = temp;
                p2++;
            }
        }
        return result;
    }

    public static void moveZero(int[] nums) {
        int p2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[p2];
                nums[p2] = nums[i];
                nums[i] = temp;
                p2++;
            }
        }
    }
}
