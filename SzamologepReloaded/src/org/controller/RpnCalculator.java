package org.controller;

import java.util.Stack;

public class RpnCalculator {

    private static Stack<Double> evalStack = new Stack<Double>();

    public static Double infixToResult(String infix) {

        String postfix = "";
        Stack<Character> operator = new Stack<Character>();
        char kivetel;
        for (int i = 0; i < infix.length(); i++) {
            char get = infix.charAt(i);
            if (!isOperator(get)) {
                postfix += get;
            } else {
                while (!operator.isEmpty() && prec(operator.peek()) >= prec(get)) {
                    postfix += operator.pop();
                }
                operator.push(get);
            }
        }
        while (!operator.isEmpty()) {
            postfix += operator.pop();
        }
        postfix = postfix.replace("+", " + ");
        postfix = postfix.replace("-", " - ");
        postfix = postfix.replace("*", " * ");
        postfix = postfix.replace("/", " / ");
        postfix = postfix.replace("  ", " ");

        char[] chars = postfix.toCharArray();
        int N = chars.length;
        for (int i = 0; i < N; i++) {
            char ch = chars[i];
            if (isOperator(ch)) {
                switch (ch) {
                    case '+':
                        evalStack.push(evalStack.pop() + evalStack.pop());
                        break;
                    case '*':
                        evalStack.push(evalStack.pop() * evalStack.pop());
                        break;
                    case '-':
                        evalStack.push(-evalStack.pop() + evalStack.pop());
                        break;
                    case '/':
                        evalStack.push(1 / evalStack.pop() * evalStack.pop());
                        break;
                }
            } else if (Character.isDigit(ch)) {
                evalStack.push(0.0);
                while (Character.isDigit(chars[i])) {
                    evalStack.push(10.0 * evalStack.pop() + (chars[i++] - '0'));
                }
            }
        }

        if (!evalStack.isEmpty()) {
            return evalStack.pop();
        } else {
            return 0.0;
        }
    }

    private static boolean isOperator(char i) {
        return prec(i) > 0;
    }

    private static int prec(char i) {
        switch (i) {
            case '-':
            case '+':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }
}
