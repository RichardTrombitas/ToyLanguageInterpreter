package Model.Data;

import Model.Values.RefValue;
import Model.Values.Value;

import java.util.HashMap;
import java.util.Map;

public class Heap implements IHeap {
    private Map<Integer, Value> map = new HashMap<>();
    private int endAddress = 0;

    public synchronized int allocate(Value val) {
        map.put(++endAddress, val);
        return endAddress;
    }

    public synchronized void deallocate(Integer addr) {
        map.remove(addr);
    }

    public synchronized Value lookup(Integer addr) {
        return map.get(addr);
    }

    public synchronized void update(Integer addr, Value val) {
        map.put(addr, val);
    }

    public synchronized boolean isDefined(Integer addr) {
        return map.containsKey(addr);
    }

    public synchronized Map<Integer, Value> getContent() {
        return map;
    }

    public synchronized void setContent(Map<Integer, Value> map) {
        this.map = map;
    }

    @Override
    public synchronized String toString() {
        return "Heap: " + map.toString();
    }

    public synchronized String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<Integer, Value> mapElement : map.entrySet()) {
            res.append(mapElement.getKey()).append(" --> ");
            res.append(mapElement.getValue()).append("\n");
        }
        return res.toString();
    }

    public synchronized Map<Integer, Value> getReachableValues(Integer initialAddr) {
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
