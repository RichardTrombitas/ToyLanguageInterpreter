package Model.Statements;

import Model.Data.IExeStack;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.Expressions.NotExp;
import Model.MyException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public class RepeatUntilStmt implements IStmt{
    private Exp exp;
    private IStmt stmt;

    public RepeatUntilStmt(IStmt stmt, Exp exp) {
        this.stmt = stmt;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "repeat(" + stmt.toString() + ") until (" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTbl();
        IExeStack stack = state.getStk();
        IHeap hp = state.getHeap();

        Value res = exp.eval(symTbl, hp);
        if (!res.getType().equals(new BoolType())) {
            throw new MyException("The expression (" + exp.toString() + ") doesn't evaluate to a BoolType!");
        }

        stack.push(new CompStmt(stmt, new WhileStmt(new NotExp(exp), stmt)));

        return null;
    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        Type typExp = exp.typecheck(typeEnv);

        if (typExp.equals(new BoolType())) {
            stmt.typecheck(new HashMap<>(typeEnv));
            return typeEnv;
        } else {
            throw new MyException("The expression (" + exp.toString() + ") doesn't evaluate to a BoolType!");
        }
    }
}
