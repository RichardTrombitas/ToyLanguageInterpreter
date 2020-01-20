package Model.Statements;

import Model.Data.IBarrierTable;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Map;

public class NewBarrierStmt implements IStmt {
    private String varName;
    private Exp exp;

    public NewBarrierStmt(String varName, Exp exp) {
        this.varName = varName;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "newBarrier(" + varName + "," + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTbl();
        IHeap hp = state.getHeap();

        Value number = exp.eval(symTbl, hp);

        Type valueType = number.getType();
        if (!valueType.equals(new IntType())) {
            throw new Error(number.toString() + " is not of IntType!");
        }

        IBarrierTable bt = state.getBarrierTable();
        int addr = bt.allocate(new Pair<>(((IntValue) number).getVal(), new ArrayList<>()));

        if (!symTbl.isDefined(varName)) {
            throw new Error("Variable " + varName + " is not defined!");
        } else {
            if (!symTbl.lookup(varName).getType().equals(new IntType())) {
                throw new Error("Variable " + varName + " is not of IntType!");
            } else {
                symTbl.update(varName, new IntValue(addr));
            }
        }

        return null;
    }

    // Implement the method typecheck for the statement newBarrier(var,
//exp) to verify if both var and exp have the type int
    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {

        Type typexp = exp.typecheck(typeEnv);
        if (typexp instanceof IntType) {
            if (!typeEnv.containsKey(varName)) {
                throw new Error("The variable " + varName + " is not defined!");
            }
            Type typevar = typeEnv.get(varName);
            if (typevar instanceof IntType) {
                return typeEnv;
            } else {
                throw new Error("The type of " + varName + " is not IntType!");
            }
        } else {
            throw new Error("The expression " + exp.toString() + " doesn't evaluate to an integer!");
        }
    }
}
