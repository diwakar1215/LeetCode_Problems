class Solution {

    static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
        }

        if (countWays(half, halfLen) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0) {
                    continue;
                }

                half[c]--;

                long ways = countWays(half, halfLen - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        ans.append(left);

        if (mid != 0) {
            ans.append(mid);
        }

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] cnt, int total) {

        long ans = 1;
        int remain = total;

        for (int x : cnt) {

            if (x == 0) {
                continue;
            }

            ans = multiply(ans, comb(remain, x));

            if (ans >= LIMIT) {
                return LIMIT;
            }

            remain -= x;
        }

        return ans;
    }

    private long comb(int n, int r) {

        if (r < 0 || r > n) {
            return 0;
        }

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            res = res * (n - r + i) / i;

            if (res >= LIMIT) {
                return LIMIT;
            }
        }

        return res;
    }

    private long multiply(long a, long b) {

        if (a == 0 || b == 0) {
            return 0;
        }

        if (a >= LIMIT || b >= LIMIT) {
            return LIMIT;
        }

        if (a > LIMIT / b) {
            return LIMIT;
        }

        return Math.min(a * b, LIMIT);
    }
}