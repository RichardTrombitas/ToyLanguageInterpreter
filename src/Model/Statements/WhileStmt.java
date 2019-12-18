package Model.Statements;

import Model.Data.*;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

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

    public PrgState execute(PrgState state) throws MyException {
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

        return null;
    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(new HashMap<>(typeEnv));
            return typeEnv;
        } else {
            throw new MyException("The WHILE expression (" + exp.toString() + ") has the type " +
                    typexp.toString() + " instead of bool!");
        }
    }
}
