package Model;

public interface ExecutionStackInterface<T> {
    public void push(T element);
    public T pop();
    public boolean isEmpty();
}
