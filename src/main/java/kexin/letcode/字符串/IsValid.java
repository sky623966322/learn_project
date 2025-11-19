package kexin.letcode.字符串;

import java.util.Stack;

public class IsValid {

    public static void main(String[] args) {
        String str = "(]";
        boolean valid = isValid(str);
        System.out.println(valid);
    }
    public static boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < charArray.length; i++) {
            char item = charArray[i];
            if (item == '(' || item == '[' || item== '{') {
                stack.push(item);
            } else if(stack.isEmpty()){
                return false;
            } else {
                Character pop = stack.pop();
                if (item == ')' && pop != '(') {
                    return false;
                }
                if (item == ']' && pop != '[') {
                    return false;
                }
                if (item == '}' && pop != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
