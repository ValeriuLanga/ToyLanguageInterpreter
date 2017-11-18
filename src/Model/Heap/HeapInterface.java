package Model.Heap;

public interface HeapInterface<K,V> {
    public void add(K key, V value);
    public void remove(K key);
    public V get(K key);
    public boolean contains(K key);
}
