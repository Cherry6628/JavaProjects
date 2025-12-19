package datastructure;

public interface MutableBinaryTree<T> extends BinaryTree<T>{
    boolean insert(T value);
    boolean delete(T value);
    boolean contains(T value);
}
