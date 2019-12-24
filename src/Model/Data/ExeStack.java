package Model.Data;

import Model.Statements.IStmt;

import java.util.ListIterator;
import java.util.Stack;


public class ExeStack implements IExeStack {
    private Stack<IStmt> stack = new Stack<>();

    public IStmt pop() {
        return stack.pop();
    }

    public void push(IStmt elem) {
        stack.push(elem);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public String toString() {
        return "ExeStack: " + stack.toString();
    }

    public String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        ListIterator<IStmt> li = stack.listIterator(stack.size());
        while (li.hasPrevious()) {
            res.append(li.previous()).append('\n');
        }
        return res.toString();
    }

    public Stack<IStmt> getContent() {
        return stack;
    }
}