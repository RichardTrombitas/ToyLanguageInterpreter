package Model.DataStructures;

public interface MyIStack<T>{
    public T pop();
    public void push(T v);
    boolean isEmpty();
}