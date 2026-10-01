class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']','[');
        for(char ch : s.toCharArray()) {
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }
            else{
                if(stack.isEmpty() || stack.pop() != map.get(ch))
                return false;
            }
        }
        return stack.isEmpty();
    }
}