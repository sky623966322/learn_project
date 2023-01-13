package kexin.letcode;

public class SortedSquares {

    public static void main(String[] args) {
        int[] nums = new int[]{-4,-1,0,3,10};
        int[] result = sortedSquares(nums);
        System.out.println(result);
    }

    public static int[] sortedSquares(int[] nums) {
        int left = 0, rigth = nums.length - 1;
        int pos = nums.length - 1; // 必须定义pos记录指针位置
        int[] result = new int[nums.length];
        while (left <= rigth) {
            int leftSquare = nums[left] * nums[left];
            int rigthSquare = nums[rigth] * nums[rigth];

            if (leftSquare > rigthSquare) { // 左边较大的时候，放到右边
                result[pos] = leftSquare;
                left ++;
            } else { // 右边较，右边放到对应的位置，一样大的时候也只处理右边的即可
                result[pos] = rigthSquare;
                rigth--;
            }
            pos--;
        }
        return result;
    }
}
