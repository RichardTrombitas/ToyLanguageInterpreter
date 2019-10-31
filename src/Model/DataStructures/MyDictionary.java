package Model.DataStructures;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    public Map<K, V> map = new HashMap<>();

    public boolean isDefined(K id) {
        return map.containsKey(id);
    }

    public V lookup(K id) {
        return map.get(id);
    }

    public void update(K id, V val) {
        map.put(id, val);
    }
}
