package Model.Statements;

import Model.Data.*;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Values.BoolValue;
import Model.Values.Value;

public class WhileStmt implements IStmt {
    private Exp exp;
    private IStmt stmt;

    public WhileStmt(Exp exp, IStmt stmt) {
        this.exp = exp;
        this.stmt = stmt;
    }

    @Override
    public String toString() {
        return "while(" + exp.toString() + "){" + stmt.toString() + "}";
    }

    public void execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTbl();
        IExeStack stack = state.getStk();
        IHeap hp = state.getHeap();

        Value val = exp.eval(symTbl, hp);
        if (val.getType().equals(new BoolType())) {
            if (((BoolValue) val).equals(new BoolValue(true))) {
                stack.push(new WhileStmt(exp, stmt));
                stack.push(stmt);
            }
        } else {
            throw new MyException("Condition expression " + exp.toString() + " is not a boolean!");
        }
    }
}
