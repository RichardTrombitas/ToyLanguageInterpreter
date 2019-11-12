package Model.DataStructures;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyDictionary<K, V> implements MyIDictionary<K, V> {
    private Map<K, V> map = new HashMap<>();

    public boolean isDefined(K id) {
        return map.containsKey(id);
    }

    public V lookup(K id) {
        return map.get(id);
    }

    public void update(K id, V val) {
        map.put(id, val);
    }

    @Override
    public String toString(){
        return map.toString();
    }

    public String toStringSpecial() {
        StringBuilder res = new StringBuilder();
        Iterator mapIterator = map.entrySet().iterator();
        for (Map.Entry mapElement : map.entrySet()) {
            res.append(mapElement.getKey()).append(" --> ");
            res.append(mapElement.getValue()).append("\n");
        }
        return res.toString();
    }

}
