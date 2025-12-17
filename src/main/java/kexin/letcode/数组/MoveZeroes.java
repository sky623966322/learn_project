package kexin.letcode.数组;

public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,1,0,3,12};
        moveZeroes3(nums);
        for (int num : nums) {
            System.out.printf(num + ", ");
        }
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

    /**
     * 思路：
     * 使用冒泡排序方法，每次遍历将0移动到最后
     *
     * @param nums
     */
    public static void moveZeroes2(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] == 0) {
                    nums[j] = nums[j+1];
                    nums[j+1] = 0;
                }
            }
        }
    }

    /**
     * 思路：
     * 1）使用p1指针快指针遍历整个数组，p2慢指针遇到0时停下；
     * 2）如果p1指针指向的元素等于0，p2停留在原地，指向0元素，p1继续往前走，
     * 3）如果不等于0，则将数字与p2的位置的0互换，p2往前走一步，
     *
     * @param nums
     */
    public static void moveZeroes3(int[] nums) {
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
