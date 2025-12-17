package kexin.letcode.数组;

public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        int count = removeElement3(nums, 3);
        System.out.println(count);
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }

    public static int removeElement2(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count] = nums[i];
                nums[i] = val;
                count++;
            }
        }
        return count;
    }

    public static int removeElement3(int[] nums, int val) {
        int rightPoint = nums.length - 1;
        int leftPoint = 0;
        while (leftPoint <= rightPoint) {
            if (nums[leftPoint] == val) {
                nums[leftPoint] = nums[rightPoint];
                nums[rightPoint] = val;
                rightPoint--;
            } else {
                leftPoint++;
            }
        }
        return leftPoint;
    }
}
