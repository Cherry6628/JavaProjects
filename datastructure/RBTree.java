package datastructure;


public class RBTree<T extends Comparable<T>> extends BinarySearchTree<T>  {
    private static final class RBNode<T> extends BinaryNode<T> {
        private boolean red = true;
        RBNode(T d) { super(d); }
        void setRed(boolean isRed) { red = isRed; }
        boolean isRed() { return red; }
    }

    @Override
    protected BinaryNode<T> createNode(T value) {
        return new RBNode<>(value);
    }

    private RBNode<T> asRB(BinaryNode<T> n) {
        return (n == null) ? null : (RBNode<T>) n;
    }

    private RBNode<T> rotateRight(RBNode<T> a) {
        RBNode<T> b = asRB(a.left());
        RBNode<T> parent = asRB(a.parent());

        if (b == null) return a;

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

        return b;
    }

    private RBNode<T> rotateLeft(RBNode<T> a) {
        RBNode<T> b = asRB(a.right());
        RBNode<T> parent = asRB(a.parent());

        if (b == null) return a;

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

        return b;
    }

    private RBNode<T> minimum(RBNode<T> node) {
        if (node == null) return null;
        RBNode<T> cur = node;
        while (cur.left() != null) cur = asRB(cur.left());
        return cur;
    }

    private RBNode<T> findNode(T value) { 
    	BinaryNode<T> cur = getRootNode(); 
    	while (cur != null) { 
    		int c = value.compareTo(cur.value()); 
    		if (c == 0) return asRB(cur); 
    		cur = (c < 0) ? cur.left() : cur.right(); 
    	} 
    	return null; 
    }

    @Override
    public boolean insert(T value) {

        if (!super.insert(value)) return false;

        RBNode<T> node = asRB(findNode(value));
        if (node == null) return true;

        node.setRed(true);

        RBNode<T> parent = asRB(node.parent());

        while (parent != null && parent.isRed()) {
            RBNode<T> grand = asRB(parent.parent());
            if (grand == null) break;

            if (parent.isLeftChild()) {
                RBNode<T> uncle = asRB(grand.right());
                if (uncle != null && uncle.isRed()) {
                    uncle.setRed(false);
                    parent.setRed(false);
                    grand.setRed(true);
                    node = grand;
                    parent = asRB(node.parent());
                } else {
                    if (node == parent.right()) {
                        node = parent;
                        rotateLeft(node);
                        parent = asRB(node.parent());
                    }
                    parent = asRB(node.parent());
                    grand = asRB(parent.parent());
                    parent.setRed(false);
                    if (grand != null) {
                        grand.setRed(true);
                        rotateRight(grand);
                    }
                    break;
                }
            } else {
                RBNode<T> uncle = asRB(grand.left());
                if (uncle != null && uncle.isRed()) {
                    uncle.setRed(false);
                    parent.setRed(false);
                    grand.setRed(true);
                    node = grand;
                    parent = asRB(node.parent());
                } else {
                    if (node == parent.left()) {
                        node = parent;
                        rotateRight(node);
                        parent = asRB(node.parent());
                    }
                    parent = asRB(node.parent());
                    grand = asRB(parent.parent());
                    parent.setRed(false);
                    if (grand != null) {
                        grand.setRed(true);
                        rotateLeft(grand);
                    }
                    break;
                }
            }
        }

        RBNode<T> root = asRB(getRootNode());
        if (root != null) root.setRed(false);
        return true;
    }

    @Override
    public boolean delete(T value) {
        RBNode<T> z = asRB(findNode(value));
        if (z == null) return false;

        RBNode<T> y = z;
        boolean yWasRed = y.isRed();
        BinaryNode<T> xNode;
        RBNode<T> xRBParent = null;

        if (z.left() == null) {
            xNode = z.right();
            xRBParent = asRB(z.parent());
            replaceParentLink(z, z.right());
        } else if (z.right() == null) {
            xNode = z.left();
            xRBParent = asRB(z.parent());
            replaceParentLink(z, z.left());
        } else {
            y = minimum(asRB(z.right()));
            if (y == null) return false;
            yWasRed = y.isRed();
            xNode = y.right();

            if (y.parent() == z) {
                xRBParent = asRB(y);
            } else {
            	replaceParentLink(y, y.right());
                y.setRight(z.right());
                if (y.right() != null) y.right().setParent(y);
                xRBParent = asRB(y.parent());
            }
            replaceParentLink(z, y);
            y.setLeft(z.left());
            if (y.left() != null) y.left().setParent(y);
            RBNode<T> yRB = asRB(y);
            if (yRB != null) yRB.setRed(z.isRed());
        }

        if (!yWasRed) {
            fixDelete(asRB(xNode), xRBParent);
        }

        decrementSize();
        return true;
    }

    private void fixDelete(RBNode<T> x, RBNode<T> parent) {
        while ((x == null || !x.isRed()) && parent != null) {
            RBNode<T> sibling;
            if (x == parent.left()) {
                sibling = asRB(parent.right());
                if (sibling != null && sibling.isRed()) {
                    sibling.setRed(false);
                    parent.setRed(true);
                    rotateLeft(parent);
                    sibling = asRB(parent.right());
                }
                if ((sibling == null) ||
                    ((sibling.left() == null || !asRB(sibling.left()).isRed()) &&
                     (sibling.right() == null || !asRB(sibling.right()).isRed()))) {
                    if (sibling != null) sibling.setRed(true);
                    x = parent;
                    parent = asRB(x.parent());
                } else {
                    if (sibling.right() == null || !asRB(sibling.right()).isRed()) {
                        if (sibling.left() != null) asRB(sibling.left()).setRed(false);
                        sibling.setRed(true);
                        rotateRight(sibling);
                        sibling = asRB(parent.right());
                    }
                    if (sibling != null) {
                        sibling.setRed(parent.isRed());
                        if (sibling.right() != null) asRB(sibling.right()).setRed(false);
                    }
                    parent.setRed(false);
                    rotateLeft(parent);
                    x = asRB(getRootNode());
                    parent = null;
                }
            } else {
                sibling = asRB(parent.left());
                if (sibling != null && sibling.isRed()) {
                    sibling.setRed(false);
                    parent.setRed(true);
                    rotateRight(parent);
                    sibling = asRB(parent.left());
                }
                if ((sibling == null) ||
                    ((sibling.left() == null || !asRB(sibling.left()).isRed()) &&
                     (sibling.right() == null || !asRB(sibling.right()).isRed()))) {
                    if (sibling != null) sibling.setRed(true);
                    x = parent;
                    parent = asRB(x.parent());
                } else {
                    if (sibling.left() == null || !asRB(sibling.left()).isRed()) {
                        if (sibling.right() != null) asRB(sibling.right()).setRed(false);
                        sibling.setRed(true);
                        rotateLeft(sibling);
                        sibling = asRB(parent.left());
                    }
                    if (sibling != null) {
                        sibling.setRed(parent.isRed());
                        if (sibling.left() != null) asRB(sibling.left()).setRed(false);
                    }
                    parent.setRed(false);
                    rotateRight(parent);
                    x = asRB(getRootNode());
                    parent = null;
                }
            }
        }
        if (x != null) x.setRed(false);
    }
	public static void main(String[]args) {
		RBTree<Integer> tree = new RBTree<>();
		int[] add = {50,25,75,12,37,62,87,6,18};
		for(int i:add)
		tree.insert(i);
		
		int[] del = {6,87};
		for (int i:del)tree.delete(i);
		System.out.println(tree.height());
		System.out.println(tree.getRootNode());
		System.out.println(tree.height());
		System.out.println(tree.inorder());
	}
}
