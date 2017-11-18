package Model.FileTable;

public interface FileTableInterface<K,V> {
    public void add(K key, V value);
    public void remove(K key);
    public V get(K key);
    public boolean contains(V value);
    public int size();
}
