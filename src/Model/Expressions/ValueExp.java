package Model.Expressions;
import Model.CollectionInstances.IHeap;
import Model.CollectionInstances.ISymTable;
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
