package Model.CollectionInstances;

public interface IExeStack<T>{
    T pop();
    void push(T v);
    boolean isEmpty();
    String toStringSpecial();
}