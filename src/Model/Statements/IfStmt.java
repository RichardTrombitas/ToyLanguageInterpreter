package Model.Statements;

import Model.Data.IExeStack;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public class IfStmt implements IStmt {
    private Exp exp;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ") THEN (" + thenS.toString() + ") ELSE (" + elseS.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        IExeStack stk = state.getStk();
        ISymTable symTable = state.getSymTbl();
        IHeap hp = state.getHeap();

        Value cond = exp.eval(symTable, hp);

        if (!cond.getType().equals(new BoolType())) {
            throw new MyException("conditional expression is not a boolean");
        } else {
            BoolValue val = (BoolValue) cond;
            if (val.getVal()) {
                stk.push(thenS);
            } else {
                stk.push(elseS);
            }
        }
        return null;
    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(new HashMap<>(typeEnv));
            elseS.typecheck(new HashMap<>(typeEnv));
            return typeEnv;
        } else {
            throw new MyException("The IF expression " + exp.toString() + " is of type " + typexp.toString() +
                    " instead of bool!");
        }
    }

}


