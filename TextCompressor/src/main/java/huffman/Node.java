package huffman;

/**
 *
 * @author holmbrob
 */
public class Node implements Comparable<Node> {
    private Character ch;
    private Integer value;
    private Node left;
    private Node right;

    /**
     * Constructor
     * @param ch, the character for the node
     * @param value, the value for the node
     */
    public Node(Character ch, Integer value) {
        this.ch = ch;
        this.value = value;
    }

    /**
     * Constructor for creating a help node that doesn't contain a character
     * @param value, the value of the help node
     */
    public Node(Integer value) {
        this.value = value;
    }

    /**
     * Method for getting the character of the node
     * @return, the character
     */
    public Character getCh() {
        return ch;
    }

    /**
     * Method for setting the character of a node
     * @param ch, the character to be set
     */
    public void setCh(Character ch) {
        this.ch = ch;
    }

    /**
     * Method for getting the value of the node
     * @return, the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * Method for setting the value of the node
     * @param value, the value to be set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * Method for getting the left child of a node
     * @return, the left child node, if it exists
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Method for setting the left child of a node
     * @param left, the node to be set as a left child
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Method for getting the right child of a node
     * @return, the right child node, if it exists
     */
    public Node getRight() {
        return right;
    }

    /**
     * Method for setting the right child of a node
     * @param right, the node to be set as a left child
     */
    public void setRight(Node right) {
        this.right = right;
    }

    @Override public int compareTo(Node node) {
        return this.value.compareTo(node.getValue());
    }
}
