package Model.CollectionInstances;

import Model.Statements.IStmt;

public interface IExeStack{
    IStmt pop();
    void push(IStmt s);
    boolean isEmpty();
    String toStringSpecial();
}