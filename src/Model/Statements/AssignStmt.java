package Model.Statements;

import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

import java.util.Map;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt(String id, Exp exp) {
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        ISymTable symTbl = state.getSymTbl();
        IHeap hp = state.getHeap();
        Value val = exp.eval(symTbl, hp);
        if (symTbl.isDefined(id)) {
            Type typId = (symTbl.lookup(id)).getType();
            if ((val.getType()).equals(typId)) {
                symTbl.update(id, val);
            } else {
                throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match ");
            }
        } else {
            throw new MyException("the used variable" + id + " was not declared before");
        }
        return null;
    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        if (!typeEnv.containsKey(id)) {
            throw new MyException("The variable " + id + " is not defined!");
        }
        Type typevar = typeEnv.get(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp)) {
            return typeEnv;
        } else {
            throw new MyException("Assignment: right hand side and left hand side have different types" +
                    " (" + typevar.toString() + " != " + typexp.toString() + ")!");
        }
    }

}

