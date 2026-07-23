class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if( n<= 2){
            return n;
        }
        else {
            return 1 << 32 - (Integer.numberOfLeadingZeros(n));
        }
    }
}