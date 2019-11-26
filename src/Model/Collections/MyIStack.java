package Model.Collections;

public interface MyIStack<T>{
    T pop();
    void push(T v);
    boolean isEmpty();
    String toStringSpecial();
}