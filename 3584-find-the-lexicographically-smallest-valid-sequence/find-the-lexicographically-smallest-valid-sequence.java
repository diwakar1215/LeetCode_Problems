class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // exact[i] = maximum suffix of word2 that can be
        // matched exactly using word1[i...]
        int[] exact = new int[n + 1];

        // almost[i] = maximum suffix of word2 that can be
        // matched with at most one mismatch using word1[i...]
        int[] almost = new int[n + 1];

        // Build DP from right to left
        for (int i = n - 1; i >= 0; i--) {

            // -------------------------
            // EXACT
            // -------------------------
            exact[i] = exact[i + 1];

            int matched = exact[i + 1];

            if (matched < m) {
                int targetIndex = m - matched - 1;

                if (word1.charAt(i) == word2.charAt(targetIndex)) {
                    exact[i] = matched + 1;
                }
            }

            // -------------------------
            // ALMOST
            // -------------------------
            almost[i] = almost[i + 1];

            // Case 1: use word1[i] as a matching character
            matched = almost[i + 1];

            if (matched < m) {
                int targetIndex = m - matched - 1;

                if (word1.charAt(i) == word2.charAt(targetIndex)) {
                    almost[i] = Math.max(almost[i], matched + 1);
                }
            }

            // Case 2: use word1[i] as the ONE mismatch
            matched = exact[i + 1];

            if (matched < m) {
                int targetIndex = m - matched - 1;

                if (word1.charAt(i) != word2.charAt(targetIndex)) {
                    almost[i] = Math.max(almost[i], matched + 1);
                }
            }
        }

        int[] answer = new int[m];

        int pos = 0;
        boolean mismatchUsed = false;

        // Greedily choose smallest indices
        for (int j = 0; j < m; j++) {

            int remaining = m - j - 1;
            boolean found = false;

            for (int i = pos; i < n; i++) {

                boolean same =
                    word1.charAt(i) == word2.charAt(j);

                boolean possible;

                if (mismatchUsed) {

                    // Mismatch already used.
                    // Current character MUST match.
                    possible = same && exact[i + 1] >= remaining;

                } else {

                    if (same) {
                        // We can still use mismatch later.
                        possible = almost[i + 1] >= remaining;
                    } else {
                        // Use our one mismatch here.
                        possible = exact[i + 1] >= remaining;
                    }
                }

                if (possible) {

                    answer[j] = i;
                    pos = i + 1;

                    if (!same) {
                        mismatchUsed = true;
                    }

                    found = true;
                    break;
                }
            }

            if (!found) {
                return new int[0];
            }
        }

        return answer;
    }
}