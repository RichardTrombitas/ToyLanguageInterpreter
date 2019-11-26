package Model.CollectionInstances;

import java.util.ListIterator;
import java.util.Stack;

public class ExeStack<T> implements IExeStack<T> {
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

    @Override
    public String toString(){
        return stack.toString();
    }

    public String toStringSpecial(){
        StringBuilder res = new StringBuilder();
        ListIterator li = stack.listIterator(stack.size());
        while (li.hasPrevious()) {
            res.append(li.previous()).append('\n');
        }
        return res.toString();
    }
}