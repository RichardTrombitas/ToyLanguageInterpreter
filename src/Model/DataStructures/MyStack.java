package Model.DataStructures;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack = new Stack<>();

    public T pop(){
        return stack.pop();
    }

    public void push(T elem){
        stack.push(elem);
    }
}