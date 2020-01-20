package Model.Data;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BarrierTable implements IBarrierTable {
    private Map<Integer, Pair<Integer, List<Integer>>> map = new HashMap<>();
    private AtomicInteger endAddress = new AtomicInteger(0);

    public int allocate(Pair<Integer, List<Integer>> val) {
        map.put(endAddress.incrementAndGet(), val);
        return endAddress.get();
    }

    public synchronized void deallocate(Integer addr) {
        map.remove(addr);
    }

    public synchronized Pair<Integer, List<Integer>> lookup(Integer addr) {
        return map.get(addr);
    }

    public synchronized void update(Integer addr, Pair<Integer, List<Integer>> val) {
        map.put(addr, val);
    }

    public synchronized boolean isDefined(Integer addr) {
        return map.containsKey(addr);
    }

    public synchronized Map<Integer, Pair<Integer, List<Integer>>> getContent() {
        return map;
    }

    public synchronized void setContent(Map<Integer, Pair<Integer, List<Integer>>> map) {
        this.map = map;
    }

    @Override
    public synchronized String toString() {
        return "BarrierTable: " + map.toString();
    }

    public synchronized String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        for (Map.Entry<Integer, Pair<Integer, List<Integer>>> mapElement : map.entrySet()) {
            res.append(mapElement.getKey()).append(" --> ");
            res.append(mapElement.getValue()).append("\n");
        }
        return res.toString();
    }
}
