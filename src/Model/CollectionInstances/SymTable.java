package Model.CollectionInstances;

import Model.Collections.MyDictionary;
import Model.Values.Value;

import java.util.Map;

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
    public Map<String, Value> getContent() {
        return dictionary.getContent();
    }

    @Override
    public void setContent(Map<String, Value> map) {
        dictionary.setContent(map);
    }

    @Override
    public String toString(){
        return "SymTable: "+dictionary.toString();
    }

    public String toStringSpecial() {
        return dictionary.toStringSpecial();
    }

}
