class Solution {
    public:
        bool isMiddleElementUnique(vector<int>& nums) {
            int mid = nums[nums.size() / 2];
            int cnt = 0;
            for(int x:nums){
                if(x==mid)
                    cnt++;
            }
            return cnt == 1;
            
        }
    };