package Model.DataStructures;

import java.util.ListIterator;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private Stack<T> stack = new Stack<>();

    public T pop(){
        return stack.pop();
    }

    public void push(T elem){
        stack.push(elem);
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

//    @Override
//    public String toString(){
//        return stack.toString();
//    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        ListIterator li = stack.listIterator(stack.size());
        while (li.hasPrevious()) {
            res.append(li.previous()).append('\n');
        }
        return res.toString();
    }
}