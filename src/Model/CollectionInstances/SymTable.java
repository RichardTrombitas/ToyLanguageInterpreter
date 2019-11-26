package Model.CollectionInstances;

import Model.Collections.MyDictionary;
import Model.Values.Value;

public class SymTable implements ISymTable {
    private MyDictionary<String, Value> dictionary = new MyDictionary<>();

    public boolean isDefined(String id) {
        return dictionary.isDefined(id);
    }

    public Value lookup(String id) {
        return dictionary.lookup(id);
    }

    public void update(String id, Value val) {
        dictionary.update(id, val);
    }

    public void delete(String id) {dictionary.delete(id);}

    @Override
    public String toString(){
        return dictionary.toString();
    }

    public String toStringSpecial() {
        return dictionary.toStringSpecial();
    }

}
