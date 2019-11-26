package Model.CollectionInstances;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FileTable<K, V> implements IFileTable<K, V> {
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

    public void delete(K id) {map.remove(id);}

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
