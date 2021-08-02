class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> nosFoundSoFar = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            if(nosFoundSoFar.containsKey(target-num)){
                return new int[]{nosFoundSoFar.get(target-num), i};
            }
            nosFoundSoFar.put(num, i);
        }
        return null;
    }
}
