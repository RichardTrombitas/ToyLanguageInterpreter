package Model.Statements;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.BoolType;
import Model.Values.Value;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp exp, IStmt thenS, IStmt elseS) {
        this.exp = exp;
        this.thenS = thenS;
        this.elseS = elseS;
    }

    @Override
    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenS.toString() + ")ELSE(" + elseS.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTable = state.getTbl();

        Value cond = exp.eval(symTable);

        if(!cond.getType().equals(new BoolType())){
            throw new MyException("conditional expression is not a boolean");
        }
        else {
            if(cond == true){
                stk.push(thenS);
            }
            else {
                stk.push(elseS);
            }
        }

        return state;
    }
    // ...
}


