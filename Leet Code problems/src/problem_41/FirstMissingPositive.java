package problem_41;

//todo not done

public class FirstMissingPositive {
    public static void main(String[] args) {
        System.out.println(new Solution().firstMissingPositive(new int[]{0,2,2,1,1}));
    }

}

class Solution {
    public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] >= nums.length || nums[i] == i + 1) {
                continue;
            }
            swap(i, nums[i], nums);
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return nums.length + 1;
    }

    private void swap(int i, int num, int[] nums) {
        if (num <= 0 || num >= nums.length || num == nums[nums[i]]) {
            nums[i] = num;
            return;
        }

        int temp = nums[num - 1];
        nums[num - 1] = num;
        swap(i, temp, nums);
    }
}
