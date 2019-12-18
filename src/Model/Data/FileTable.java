package Model.Data;

import Model.Values.StringValue;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class FileTable implements IFileTable {
    private Map<StringValue, BufferedReader> map = new HashMap<>();

    public synchronized boolean isDefined(StringValue id) {
        return map.containsKey(id);
    }

    public synchronized BufferedReader lookup(StringValue id) {
        return map.get(id);
    }

    public synchronized void update(StringValue id, BufferedReader val) {
        map.put(id, val);
    }

    public synchronized void delete(StringValue id) {
        map.remove(id);
    }

    @Override
    public synchronized String toString() {
        return "FileTable: " + map.toString();
    }

    public synchronized String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<StringValue, BufferedReader> mapElement : map.entrySet()) {
            res.append(mapElement.getKey()).append(" --> ");
            res.append(mapElement.getValue()).append("\n");
        }
        return res.toString();
    }

}
