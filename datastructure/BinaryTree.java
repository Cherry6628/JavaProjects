package datastructure;
import java.util.List;
import java.util.Objects;


public interface BinaryTree<T>{
	class BinaryNode<T>{
		private BinaryNode<T> left;
		private BinaryNode<T> right;
		private BinaryNode<T> parent;
		private T data;
		BinaryNode(T data){this.data=data;}
		BinaryNode<T> left() {return left;}
		BinaryNode<T> right() {return right;}
		BinaryNode<T> parent() {return parent;}
		public void setLeft(BinaryNode<T> c) {
		    left = c;
		    if (c != null) c.parent = this;
		}
		public void setRight(BinaryNode<T> c) {
		    right = c;
		    if (c != null) c.parent = this;
		}
		protected void setParent(BinaryNode<T> p) {
			parent=p;
		}
		public void setData(T e) {this.data=e;}
		T value() {return data;}
		public boolean isLeftChild() {
		    return parent != null && parent.left == this;
		}
		public boolean isRightChild() {
		    return parent != null && parent.right == this;
		}
		public boolean equals(Object o) {
			if(o==null)return false;
			if(o==this)return true;
			if(!(o instanceof BinaryNode<?>))return false;
			BinaryNode<?> other = (BinaryNode<?>) o;
			return Objects.equals(other.data, this.data);
		}
		public int hashCode() {
			return Objects.hash(this.data);
		}
		public String toString() {
			return data.toString();
		}
	}
	T getRoot();
	List<T> inorder();
	List<T> preorder();
	List<T> postorder();
	boolean isEmpty();
	int size();
	int height();
}
