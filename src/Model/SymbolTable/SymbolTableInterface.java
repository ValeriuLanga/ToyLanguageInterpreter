package Model.SymbolTable;

public interface SymbolTableInterface<K,V>
{
    public V get(K key);
    public boolean contains(K key);
    public void add(K key, V value);
    public void update(K key, V value);
    public void put(K key, V Value);
    public void replace(K key, V value);
}
