package datastructure;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> implements MutableBinaryTree<T>{
	private BinaryNode<T> root;
	private int size=0;
	BinarySearchTree(T data){
		this.root = createNode(data);
		this.size=1;
	}
	
	BinarySearchTree(){
		this.root=null;
		this.size=0;
	}
	protected BinaryNode<T> createNode(T value) {
	    return new BinaryNode<>(value);
	}
	
	protected void setRootNode(BinaryNode<T> node){this.root = node;}
	protected BinaryNode<T> getRootNode(){return this.root;}
	
	@Override
	public T getRoot() {
		if(this.root==null)return null;
		return this.root.value();
	}
	private List<T> inorder(BinaryNode<T> node, List<T> list){
		if (node==null)return list;
		
		inorder(node.left(), list);
		
		list.add(node.value());
		
		inorder(node.right(), list);
		
		return list;
	}
	@Override
	public List<T> inorder(){
		List<T> data = new ArrayList<>(size);
		inorder(this.root, data);
		return data;
	}

	private List<T> preorder(BinaryNode<T> node, List<T> list){
		if (node==null)return list;
		
		list.add(node.value());
		
		preorder(node.left(), list);
		
		preorder(node.right(), list);
		
		return list;
	}
	public void clear() {
		this.root=null;
	}
	@Override
	public List<T> preorder(){
		List<T> data = new ArrayList<>(size);
		preorder(this.root, data);
		return data;
	}
	
	private List<T> postorder(BinaryNode<T> node, List<T> list){
		if (node==null)return list;
		
		postorder(node.left(), list);
		
		postorder(node.right(), list);
		
		list.add(node.value());
		
		return list;
	}
	@Override
	public List<T> postorder(){
		List<T> data = new ArrayList<>(size);
		postorder(this.root, data);
		return data;
	}
	
	@Override
	public int size(){return this.size;}
	
	@Override
	public int height(){return height(root);}
	private int height(BinaryNode<T> node) {
		if(node==null)return 0;
		return 1+Math.max(height(node.left()),height(node.right()));
	}

	@Override
	public boolean insert(T value) {
	    BinaryNode<T> n = createNode(value);
	    if (root == null) {
	        root = n;
	        size++;
	        return true;
	    }
	    BinaryNode<T> temp = root;
	    while (true) {
	        int c = value.compareTo(temp.value());
	        if (c == 0) return false;
	        if (c > 0) {
	            if (temp.right() == null) {
	                temp.setRight(n);
	                size++;
	                return true;
	            }
	            temp = temp.right();
	        } else {
	            if (temp.left() == null) {
	                temp.setLeft(n);
	                size++;
	                return true;
	            }
	            temp = temp.left();
	        }
	    }
	}
	
	@Override
	public boolean delete(T value) {
	    BinaryNode<T> node = root;
	    while (node != null && !Objects.equals(node.value(), value)) {
	        int c = value.compareTo(node.value());
	        node = (c < 0) ? node.left() : node.right();
	    }
	    if (node == null) return false;

	    deleteNode(node,false);
	    decrementSize();
	    return true;
	}
	protected void decrementSize() {
	    this.size--;
	}

	private void deleteNode(BinaryNode<T> node, boolean useInorderSuccessor) {
	    if (node.left() == null && node.right() == null) {
	        replaceParentLink(node, null);
	    } else if (node.left() == null) {
	        replaceParentLink(node, node.right());
	    } else if (node.right() == null) {
	        replaceParentLink(node, node.left());
	    } else {
	        BinaryNode<T> element;
	        if(useInorderSuccessor) {
	        	element=node.right();
		        while (element.left() != null) element = element.left();
	        }
	        else{
	        	element=node.left();
	        	while (element.right()!=null) element=element.right();
	        }
	        node.setData(element.value());
	        deleteNode(element, useInorderSuccessor);
	    }
	}
	protected void replaceParentLink(BinaryNode<T> node, BinaryNode<T> newChild) {
	    if (node.parent() == null) {
	        root = newChild;
	        if (root != null) root.setParent(null);
	    } else if (node.isLeftChild()) {
	        node.parent().setLeft(newChild);
	    } else {
	        node.parent().setRight(newChild);
	    }
	}
	
	@Override
	public boolean contains(T value) {
		BinaryNode<T> temp=this.root;
		while(temp!=null && !Objects.equals(temp.value(),value)){
			int c = value.compareTo(temp.value());
			if (c<0)temp=temp.left();
			else if (c>0)temp=temp.right();
		}
		return temp!=null;
	}

	@Override
	public boolean isEmpty() {
		return this.root==null;
	}
	
	public List<T> rangeSearchMin(T min) {
	    List<T> out = new ArrayList<>();
	    rangeSearchMin(getRootNode(), min, out);
	    return out;
	}
	public List<T> rangeSearchMax(T max) {
	    List<T> out = new ArrayList<>();
	    rangeSearchMax(getRootNode(), max, out);
	    return out;
	}

	private void rangeSearchMin(BinaryNode<T> node, T min, List<T> out) {
	    if (node == null) return;
	    if(min==null)return;
	    int cmp = min.compareTo(node.value());
	    if (cmp <= 0) {
	        rangeSearchMin(node.left(), min, out);
	        out.add(node.value());
	    }
	    rangeSearchMin(node.right(), min, out);
	}
	private void rangeSearchMax(BinaryNode<T> node, T max, List<T> out) {
		if (node==null) return;
		if (max==null)return;
		int cmp = max.compareTo(node.value());
		if(cmp>=0) {
			rangeSearchMax(node.right(), max, out);
			out.add(node.value());
		}
		rangeSearchMax(node.left(), max, out);
	}
}
