package treebase;

import java.util.*;

public class KDT<E extends Comparable<E>> extends BST<E> {

    /**
     * Nested static class for a binary search tree node.
     */

    protected static class Node<E> {
        private E element;          // an element stored at this node
        private BoundingBox<Object> box;
        private Node<E> left;       // a reference to the left child (if any)
        private Node<E> right;      // a reference to the right child (if any)

        /**
         * Constructs a node with the given element and neighbors.
         *
         * @param e          the element to be stored
         * @param leftChild  reference to a left child node
         * @param rightChild reference to a right child node
         */
        public Node(E e, Node<E> leftChild, Node<E> rightChild) {
            element = e;
            left = leftChild;
            right = rightChild;
            box = new BoundingBox<Object>(e);
        }

        // accessor methods
        public E getElement() {
            return element;
        }

        public BoundingBox<Object> getBox() {
            return box;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        // update methods
        public void setElement(Object e) {
            element = (E) e;
        }

        public void setLeft(Node<E> leftChild) {
            left = leftChild;
        }

        public void setRight(Node<E> rightChild) {
            right = rightChild;
        }
    }

    //----------- end of nested Node class -----------

    protected Node<E> root = null;     // root of the tree
    int kdim = 0;
    ArrayList<Comparator<E>> comparators = new ArrayList<>();

    /* Constructs an empty binary search tree. */
    public KDT(int k) {
        super();
        kdim = k;
    }

    public void addComparator(Comparator<E> a) {
        if (comparators.size() < kdim) {
            comparators.add(a);
        }
    }

    /**
     * Inserts an element in the tree.
     */
    public void insert(E element) {
        root = insert(element, root, -1);
    }

    private Node<E> insert(E element, Node<E> node, int level) {
        if (node == null)
            return new Node<>(element, null, null);

        level++;
        if (level >= kdim) {
            level -= kdim;
        }

        Comparator current = comparators.get(level);
        if (current.compare(node.getElement(), new Node(element, null, null).getElement()) > 0) {
            node.setLeft(insert(element, node.getLeft(), level));
        } else if (current.compare(node.getElement(), new Node(element, null, null).getElement()) < 0) {
            node.setRight(insert(element, node.getRight(), level));
        } else {
            node.setElement(new Node<>(element, null, null));
        }
        return node;
    }

    public void defineBoundingBoxes() {
        Iterator<Node<E>> treeIT = posOrderNode().iterator();
        while (treeIT.hasNext()) {
            Node<E> testing = treeIT.next();
            if (testing.getLeft() != null) {
                testing.getBox().updateBox(testing.getLeft().getElement());
            } else if (testing.getRight() != null) {
                testing.getBox().updateBox(testing.getRight().getElement());
            }
        }
    }

    /**
     * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
     */
    public void remove(E element) {
        root = remove(element, this.root, -1);
    }

    private Node<E> remove(E element, Node<E> node, int level) {

        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }

        level++;
        if (level >= kdim) {
            level -= kdim;
        }

        if (element.compareTo(node.getElement()) == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }

            //smallestElement tem que implementar o comparator escolhido
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight(), level));
        } else if (comparators.get(level).compare(element, node.getElement()) < 0)
            node.setLeft(remove(element, node.getLeft(), level));
        else
            node.setRight(remove(element, node.getRight(), level));

        return node;
    }


    private E smallestElement(Node<E> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() != null) {
            return smallestElement(node.getLeft());
        } else {
            return node.getElement();
        }
    }

    public Iterable<Node<E>> posOrderNode() {
        List<Node<E>> l = new LinkedList<Node<E>>();
        posOrderNodeSubtree(root, l);
        return l;
    }

    /**
     * Adds positions of the subtree rooted at Node node to the given
     * snapshot using an post-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void posOrderNodeSubtree(Node<E> node, List<Node<E>> snapshot) {
        if (node == null) {
            return;
        }
        posOrderNodeSubtree(node.getLeft(), snapshot);
        posOrderNodeSubtree(node.getRight(), snapshot);
        snapshot.add(node);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, 0, sb);
        return sb.toString();
    }

    private void toStringRec(Node<E> root, int level, StringBuilder sb) {
        if (root == null)
            return;
        toStringRec(root.getRight(), level + 1, sb);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++)
                sb.append("|\t");
            sb.append("|-------").append(root.getElement()).append(root.getBox()).append("\n");
        } else
            sb.append(root.getElement()).append("\n");
        toStringRec(root.getLeft(), level + 1, sb);
    }

    public Comparator<E> getComparator(int level) {
        return comparators.get(level);
    }
}
