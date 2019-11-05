package Model.Statements;

import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStmt implements IStmt {
    private String id;
    private Exp exp;

    public AssignStmt(String id, Exp exp){
        this.id = id;
        this.exp = exp;
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        stk.pop();
        MyIDictionary<String, Value> symTbl = state.getTbl();
        Value val = exp.eval(symTbl);
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
        return state;
    }
}

