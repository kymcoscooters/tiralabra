package com.kymcoscooters.textcompressor;

public class Node {
    private Character ch;
    private Integer value;
    private Node left;
    private Node right;

    public Node(Character ch, Integer value) {
        this.ch = ch;
        this.value = value;
    }

    public Node(Integer value) {
        this.value = value;
    }

    public Character getCh() {
        return ch;
    }

    public void setCh(Character ch) {
        this.ch = ch;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
