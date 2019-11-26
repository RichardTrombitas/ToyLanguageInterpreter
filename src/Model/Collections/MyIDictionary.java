package Model.Collections;

import java.util.Map;

public interface MyIDictionary<K, V> {
    boolean isDefined(K id);
    V lookup(K id);
    void update(K id, V val);
    void delete(K id);
    String toStringSpecial();
    Map<K, V> getContent();
    void setContent(Map<K, V> map);
}
