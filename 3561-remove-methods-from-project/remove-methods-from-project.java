import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {

        // Build graph
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        // Find all suspicious methods using BFS
        boolean[] suspicious = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        q.offer(k);
        suspicious[k] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : graph[curr]) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    q.offer(next);
                }
            }
        }

        // Check if any non-suspicious method invokes a suspicious one
        for (int[] edge : invocations) {
            int from = edge[0];
            int to = edge[1];

            if (!suspicious[from] && suspicious[to]) {
                // Cannot remove, return all methods
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }
                return ans;
            }
        }

        // Return remaining (non-suspicious) methods
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}