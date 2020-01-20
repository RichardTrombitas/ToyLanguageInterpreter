package Model.Statements;

import Model.Data.IBarrierTable;
import Model.Data.IExeStack;
import Model.Data.ISymTable;
import Model.MyException;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public class AwaitStmt implements IStmt {
    private String varName;

    public AwaitStmt(String varName) {
        this.varName = varName;
    }

    @Override
    public String toString() {
        return "await(" + varName + ")";
    }

    public PrgState execute(PrgState state)  {
        ISymTable symTbl = state.getSymTbl();
        IBarrierTable bt = state.getBarrierTable();
        IExeStack stk = state.getStk();
        if (!symTbl.isDefined(varName)) {
            throw new Error(varName + " is not a variable in the SymTable!");
        }

        Value foundIndex = symTbl.lookup(varName);

        Type varType = foundIndex.getType();

        if (!(varType instanceof IntType)) {
            throw new Error("The type of " + foundIndex + " is not IntType!");
        }

        int idx = ((IntValue) foundIndex).getVal();

        if (!bt.isDefined(idx)) {
            throw new Error(idx + " is not an index in the BarrierTable!");
        }

        Pair<Integer, List<Integer>> pair = bt.lookup(idx);

        List<Integer> List1 = pair.getValue();
        int NL = List1.size();
        int N1 = pair.getKey();

        if (N1 > NL) {
            if (!List1.contains(state.getThreadID())) {
                List1.add(state.getThreadID());
            }
            stk.push(new AwaitStmt(varName));
        }

        return null;

    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        if (!typeEnv.containsKey(varName)) {
            throw new Error("The variable " + varName + " is not defined!");
        }
        Type typevar = typeEnv.get(varName);
        if (typevar instanceof IntType) {
            return typeEnv;
        } else {
            throw new Error("The type of " + varName + " is not IntType!");
        }
    }
}
