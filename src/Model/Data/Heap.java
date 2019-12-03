package Model.Data;

import Model.Values.RefValue;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public class Heap implements IHeap {
    private Map<Integer, Value> map = new HashMap<>();
    private int endAddress = 0;

    public int allocate(Value val) {
        map.put(++endAddress, val);
        return endAddress;
    }

    public void deallocate(Integer addr) {
        map.remove(addr);
    }

    public Value lookup(Integer addr) {
        return map.get(addr);
    }

    public void update(Integer addr, Value val) {
        map.put(addr, val);
    }

    public boolean isDefined(Integer addr) {
        return map.containsKey(addr);
    }

    public Map<Integer, Value> getContent() {
        return map;
    }

    public void setContent(Map<Integer, Value> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Heap: " + map.toString();
    }

    public String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<Integer, Value> mapElement : map.entrySet()) {
            res.append(mapElement.getKey()).append(" --> ");
            res.append(mapElement.getValue()).append("\n");
        }
        return res.toString();
    }

    public Map<Integer, Value> getReachableValues(Integer initialAddr) {
        Map<Integer, Value> resMap = new HashMap<>();
        Value val = map.get(initialAddr);
        while (val instanceof RefValue) {
            int addr = ((RefValue) val).getAddr();
            val = map.get(addr);
            resMap.put(addr, val);
        }
        return resMap;
    }
}
