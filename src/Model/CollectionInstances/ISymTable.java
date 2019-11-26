package Model.CollectionInstances;

public interface ISymTable<K, V> {
    boolean isDefined(K id);
    V lookup(K id);
    void update(K id, V val);
    void delete(K id);
    String toStringSpecial();
}
