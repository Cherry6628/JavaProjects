package datastructure;


public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    private static final class AVLNode<T> extends BinaryNode<T> {
        int height = 1;
        AVLNode(T d) { super(d); }
    }
    public AVLTree(T data){
		super(data);
	}
	
	public AVLTree(){
		super();
	}
    @Override
    protected BinaryNode<T> createNode(T value) {
        return new AVLNode<>(value);
    }

    private AVLNode<T> asAVL(BinaryNode<T> n) {
        return (n == null) ? null : (AVLNode<T>) n;
    }

    private int height(AVLNode<T> n) {
        return (n == null) ? 0 : n.height;
    }

    private int balance(AVLNode<T> n) {
        return height(asAVL(n.left())) - height(asAVL(n.right()));
    }


    @Override
    public boolean insert(T value) {
        if (!super.insert(value)) return false;
        rebalanceUpwards(findNode(value));
        return true;
    }
    

    private BinaryNode<T> deleteNodeForAVL(BinaryNode<T> node) {

        if (node.left() == null || node.right() == null) {
            BinaryNode<T> child = (node.left() != null) ? node.left() : node.right();
            BinaryNode<T> parent = node.parent();

            replaceParentLink(node, child);
            decrementSize();

            return parent;
        }

        BinaryNode<T> succ = node.right();
        while (succ.left() != null) succ = succ.left();

        node.setData(succ.value());

        return deleteNodeForAVL(succ);
    }

    @Override
    public boolean delete(T value) {
        AVLNode<T> node = findNode(value);
        if (node == null) return false;

        AVLNode<T> start = asAVL(deleteNodeForAVL(node)); 
        rebalanceUpwards(start);
        return true;
    }



    private AVLNode<T> findNode(T value) {
        BinaryNode<T> cur = getRootNode();
        while (cur != null) {
            int c = value.compareTo(cur.value());
            if (c == 0) return asAVL(cur);
            cur = (c < 0) ? cur.left() : cur.right();
        }
        return null;
    }


    private AVLNode<T> rotateRight(AVLNode<T> a) {
        AVLNode<T> b = asAVL(a.left());
        AVLNode<T> parent = asAVL(a.parent());

        if (parent == null) {
            setRootNode(b);
            b.setParent(null);
        } else if (a.isLeftChild()) {
            parent.setLeft(b);
        } else {
            parent.setRight(b);
        }

        a.setLeft(b.right());
        b.setRight(a);

        a.height = 1 + Math.max(height(asAVL(a.left())), height(asAVL(a.right())));
        b.height = 1 + Math.max(height(asAVL(b.left())), height(asAVL(b.right())));

        return b;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> a) {
        AVLNode<T> b = asAVL(a.right());
        AVLNode<T> parent = asAVL(a.parent());

        if (parent == null) {
            setRootNode(b);
            b.setParent(null);
        } else if (a.isLeftChild()) {
            parent.setLeft(b);
        } else {
            parent.setRight(b);
        }

        a.setRight(b.left());
        b.setLeft(a);

        a.height = 1 + Math.max(height(asAVL(a.left())), height(asAVL(a.right())));
        b.height = 1 + Math.max(height(asAVL(b.left())), height(asAVL(b.right())));

        return b;
    }


    private void rebalanceUpwards(AVLNode<T> node) {
        while (node != null) {
            node.height = 1 + Math.max(height(asAVL(node.left())), height(asAVL(node.right())));
            int bf = balance(node);

            if (bf > 1) { //left heavy
                AVLNode<T> L = asAVL(node.left());
                if (balance(L) < 0) // lr
                    rotateLeft(L);
                node = rotateRight(node); // ll
            }
            else if (bf < -1) { // right-heavy
                AVLNode<T> R = asAVL(node.right());
                if (balance(R) > 0)      // RL
                    rotateRight(R);
                node = rotateLeft(node); // RR
            }

            node = asAVL(node.parent());
        }
    }
	
}

