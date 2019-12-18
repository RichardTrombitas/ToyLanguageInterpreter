package Model.Statements;

import Model.Data.IFileTable;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Expressions.Exp;
import Model.MyException;
import Model.PrgState;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class ReadFileStmt implements IStmt {
    private Exp exp;
    private String varName;

    public ReadFileStmt(Exp exp, String varName) {
        this.exp = exp;
        this.varName = varName;
    }

    @Override
    public String toString() {
        return "Read from the file " + exp.toString() + " a value for " + varName;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        ISymTable symTbl = state.getSymTbl();
        IFileTable ft = state.getFileTable();
        IHeap hp = state.getHeap();

        if (!symTbl.isDefined(varName)) {
            throw new MyException("The variable is not defined in the symbol table!");
        } else if (!symTbl.lookup(varName).getType().equals(new IntType())) {
            throw new MyException("The variable is not of IntType!");
        }

        Value val = exp.eval(symTbl, hp);
        if (!val.getType().equals(new StringType())) {
            throw new MyException("The type of the evaluated expression is not of StringType!");
        }

        if (!ft.isDefined((StringValue) val)) {
            throw new MyException("There is no entry associated to this value in the FileTable!");
        }

        BufferedReader br = ft.lookup((StringValue) val);

        String line = br.readLine();

        if (line != null) {
            symTbl.update(varName, new IntValue(Integer.parseInt(line)));
        } else {
            symTbl.update(varName, new IntValue(0));
        }
        return null;
    }

    public Map<String, Type> typecheck(Map<String, Type> typeEnv) throws MyException {
        if(!typeEnv.containsKey(varName)){
            throw new MyException("The variable " + varName + " is not defined!");
        }
        Type typevar = typeEnv.get(varName);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new IntType())) {
            if (typexp.equals(new StringType())) {
                return typeEnv;
            } else {
                throw new MyException("The variable is not of StringType!");
            }
        } else {
            throw new MyException("The variable is not of IntType!");
        }
    }

}
