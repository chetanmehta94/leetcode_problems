class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Map<Integer, Integer> elementFreq = new HashMap<>();
        for(int num:nums) {
            int freq = elementFreq.getOrDefault(num, 0);
            elementFreq.put(num, ++freq);
        }
        
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(Map.Entry<Integer, Integer> entry: elementFreq.entrySet()) {
            int num = entry.getKey();
            int freq = entry.getValue();
            int existingSubsetsCount = result.size();
            for(int i=0; i<existingSubsetsCount; i++) {
                List<Integer> existingSubset = result.get(i);
                List<Integer> suffix = new ArrayList<>();
                for(int j=1; j<=freq; j++) {
                    List<Integer> existingSubsetClone = new ArrayList<>(existingSubset);
                    suffix.add(num);
                    existingSubsetClone.addAll(suffix);
                    result.add(existingSubsetClone);
                }
            }
        }
        return result;
    }
}
