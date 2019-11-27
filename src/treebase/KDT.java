package treebase;

import java.util.ArrayList;
import java.util.Comparator;

public class KDT<E extends Comparable<E>> extends BST<E> {

    protected BST.Node<E> root = null;     // root of the tree
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

    private BST.Node<E> insert(E element, BST.Node<E> node, int level) {
        if (node == null)
            return new BST.Node(element, null, null);

        level++;
        if (level >= kdim) {
            level -= kdim;
        }

        Comparator current = comparators.get(level);
        if (current.compare(node.getElement(), element) > 0) {
            node.setLeft(insert(element, node.getLeft(), level));
        } else if (current.compare(node.getElement(), element) < 0) {
            node.setRight(insert(element, node.getRight(), level));
        } else {
            node.setElement(element);
        }
        return node;
    }

    /**
     * Removes an element from the tree maintaining its consistency as a Binary Search Tree.
     */
    public void remove(E element) {
        root = remove(element, this.root, -1);
    }

    private BST.Node<E> remove(E element, BST.Node<E> node, int level) {

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

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @return the Node that contains the Element, or null otherwise
     * <p>
     * This method despite not being essential is very useful.
     * It is written here in order to be used by this class and its
     * subclasses avoiding recoding.
     * So its access level is protected
     */
    protected BST.Node<E> find(E element, BST.Node<E> node) {
        if (node == null) {
            return null;
        }
        if (element.compareTo(node.getElement()) == 0) {
            return node;
        }
        if (element.compareTo(node.getElement()) < 0) {
            return find(element, node.getLeft());
        }
        return find(element, node.getRight());
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
            sb.append("|-------" + root.getElement() + "\n");
        } else
            sb.append(root.getElement() + "\n");
        toStringRec(root.getLeft(), level + 1, sb);
    }

}
