package Model.Expressions;
import Model.Data.IHeap;
import Model.Data.ISymTable;
import Model.Values.Value;
public class ValueExp implements Exp {
    private Value e;

    public ValueExp(Value e){
        this.e = e;
    }

    @Override
    public String toString(){
        return e.toString();
    }

    @Override
    public Value eval(ISymTable symTable, IHeap hp){
        return e;
    }

}
