package Model.Data;

import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class FileTable implements IFileTable {
    private Map<StringValue, BufferedReader> map = new HashMap<>();

    public boolean isDefined(StringValue id) {
        return map.containsKey(id);
    }

    public BufferedReader lookup(StringValue id) {
        return map.get(id);
    }

    public void update(StringValue id, BufferedReader val) {
        map.put(id, val);
    }

    public void delete(StringValue id) {
        map.remove(id);
    }

    @Override
    public String toString(){
        return "FileTable: "+map.toString();
    }

    public String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<StringValue, BufferedReader> mapElement : map.entrySet()) {
            res.append(mapElement.getKey()).append(" --> ");
            res.append(mapElement.getValue()).append("\n");
        }
        return res.toString();
    }

}
