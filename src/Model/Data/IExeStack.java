package Model.Data;

import Model.Statements.IStmt;

import java.util.Stack;

public interface IExeStack {
    IStmt pop();

    void push(IStmt s);

    boolean isEmpty();

    String toStringSpecial();

    Stack<IStmt> getContent();
}