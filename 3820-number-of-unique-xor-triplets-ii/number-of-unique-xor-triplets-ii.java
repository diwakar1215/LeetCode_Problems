class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] present = new boolean[2048];

        for (int x : nums)
            present[x] = true;

        boolean[] dp = new boolean[2048];
        dp[0] = true;

        for (int step = 0; step < 3; step++) {
            boolean[] next = new boolean[2048];

            for (int xor = 0; xor < 2048; xor++) {
                if (!dp[xor]) continue;

                for (int v = 0; v < 2048; v++) {
                    if (present[v]) {
                        next[xor ^ v] = true;
                    }
                }
            }

            dp = next;
        }

        int ans = 0;
        for (boolean b : dp)
            if (b) ans++;

        return ans;
    }
}