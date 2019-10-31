package Model.Statements;
import Model.DataStructures.MyIDictionary;
import Model.DataStructures.MyIStack;
import Model.MyException;
import Model.PrgState;
import Model.Types.Type;
import Model.Values.Value;

public class VarDeclStmt implements IStmt {
    private String name;
    private Type typ;

    public VarDeclStmt(String name, Type typ){
        this.name = name;
        this.typ = typ;
    }

    @Override
    public String toString(){ return typ + " " + name; }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        stk.pop();

        MyIDictionary<String, Value> symTable = state.getTbl();
        if(symTable.isDefined(name)) {
            throw new MyException("variable is already declared");
        }
        else {
            symTable.add? or update? not sure (defaultvalue)
        }
        return state;
    }


}
