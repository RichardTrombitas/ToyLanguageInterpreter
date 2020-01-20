package Model.Data;

import Model.Values.Value;
import javafx.util.Pair;

import java.util.List;
import java.util.Map;

public interface IBarrierTable {
    int allocate(Pair<Integer, List<Integer>> val);

    void deallocate(Integer addr);

    Pair<Integer, List<Integer>> lookup(Integer addr);

    void update(Integer addr, Pair<Integer, List<Integer>> val);

    Map<Integer, Pair<Integer, List<Integer>>> getContent();

    void setContent(Map<Integer, Pair<Integer, List<Integer>>> map);

    boolean isDefined(Integer addr);

    String toStringSpecial();
}
