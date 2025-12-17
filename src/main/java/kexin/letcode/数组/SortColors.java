package kexin.letcode.数组;

public class SortColors {

    public static void main(String[] args) {
        int[] nums = new int[]{2,0,2,1,1,0};
        sortColors3(nums);
        for (int num : nums) {
            System.out.print(num + ", ");
        }
    }

    public static void sortColors3(int[] nums) {
        // 把0移动到最右边
        int p2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2++;
            }
        }
        // 把1移动到最后一个0的后面
        for (int i = p2; i < nums.length; i++) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2++;
            }
        }
    }

    public static void sortColors2(int[] nums) {
        // 把2移动到最右边
        int p2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2++;
            }
        }
        // 把0移动到最左边
        p2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                p2++;
            }
        }
    }

    public static void sortColors(int[] nums) {
        for(int i = 0; i < nums.length - 1; i++) {
            for(int j = 0; j < nums.length - 1- i; j++) {
                if(nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
    }
}
