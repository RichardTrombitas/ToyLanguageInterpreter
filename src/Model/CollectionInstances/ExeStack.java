package Model.CollectionInstances;

import Model.Collections.MyStack;
import Model.Statements.IStmt;


public class ExeStack implements IExeStack {
    private MyStack<IStmt> stack = new MyStack<>();

    public IStmt pop(){
        return stack.pop();
    }

    public void push(IStmt elem){
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
        return stack.toStringSpecial();
    }
}