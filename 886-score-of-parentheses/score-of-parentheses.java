class Solution {
    public int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                stack.push(0);
            } else {
                int current = stack.pop();

                int value;
                if (current == 0) {
                    value = 1;          // ()
                } else {
                    value = 2 * current; // (A)
                }

                stack.push(stack.pop() + value);
            }
        }

        return stack.pop();
    }
}