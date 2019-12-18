package Model.Data;

import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public class SymTable implements ISymTable {
    private Map<String, Value> map = new HashMap<>();

    public boolean isDefined(String id) {
        return map.containsKey(id);
    }

    public Value lookup(String id) {
        return map.get(id);
    }

    public void update(String id, Value val) {
        map.put(id, val);
    }

    public void delete(String id) {map.remove(id);}

    @Override
    public Map<String, Value> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<String, Value> givenMap) {
        map = givenMap;
    }

    @Override
    public ISymTable deepCopy(){
        ISymTable s = new SymTable();

        Map<String, Value> mapCopy = new HashMap<>();
        for (Map.Entry<String, Value> entry : map.entrySet())
        {
            mapCopy.put(entry.getKey(), entry.getValue().copy());
        }
        s.setContent(mapCopy);
        return s;
    }

    @Override
    public String toString(){
        return "SymTable: "+map.toString();
    }

    public String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Value> mapElement : map.entrySet()) {
            res.append(mapElement.getKey()).append(" --> ");
            res.append(mapElement.getValue()).append("\n");
        }
        return res.toString();
    }

}
