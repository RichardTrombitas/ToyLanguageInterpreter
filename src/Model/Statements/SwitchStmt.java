package Model.Statements;

import Model.Data.IExeStack;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.Expressions.ValueExp;
import Model.MyException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public class SwitchStmt implements IStmt {
    private Exp exp;
    private Exp exp1;
    private Exp exp2;
    private IStmt stmt1;
    private IStmt stmt2;
    private IStmt stmt3;

    public SwitchStmt(Exp exp, Exp exp1, Exp exp2, IStmt stmt1, IStmt stmt2, IStmt stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public String toString() {
        return "switch(" + exp.toString() + ") (case " + exp1.toString() + ": " +
                stmt1.toString() + ") (case " + exp2.toString() + ": " +
                stmt2.toString() + ") (default: " + stmt3.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        IExeStack stk = state.getStk();
        ISymTable symTable = state.getSymTbl();
        IHeap hp = state.getHeap();

        Value val = exp.eval(symTable, hp);
        Value val1 = exp1.eval(symTable, hp);
        Value val2 = exp2.eval(symTable, hp);

        IStmt ifStmt = new IfStmt(new ValueExp(new BoolValue(val.equals(val1))), stmt1,
                new IfStmt(new ValueExp(new BoolValue(val.equals(val2))), stmt2, stmt3));

        stk.push(ifStmt);

        return null;
    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
            stmt1.typecheck(new HashMap<>(typeEnv));
            stmt2.typecheck(new HashMap<>(typeEnv));
            stmt3.typecheck(new HashMap<>(typeEnv));
            return typeEnv;
        }
}



