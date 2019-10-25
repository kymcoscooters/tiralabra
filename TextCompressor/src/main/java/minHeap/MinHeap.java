package minHeap;

import huffman.Node;

/**
 *
 * @author holmbrob
 */
public class MinHeap {
    private Node[] arr;
    private int size;

    /**
     * Constructor for the min heap
     */
    public MinHeap() {
        arr = new Node[1000];
        size = 0;
    }

    /**
     * Method for pushing a node to the heap
     * @param node, the node that is being pushed
     */
    public void push(Node node) {
        arr[size] = node;
        int current = size;
        this.size++;
        while (arr[current].getValue() < arr[parent(current)].getValue()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    /**
     * Method for taking the smallest value out of the heap
     * @return, the node with the smallest value
     */
    public Node pop() {
        Node popped = arr[0];
        arr[0] = arr[size-1];
        size--;
        heapify(0);
        return popped;
    }

    /**
     * Method for checking the smallest value of the heap without removing it
     * @return, the node with the smallest value
     */
    public Node peek() {
        return arr[0];
    }

    private int parent (int index) {
        return (index-1)/2;
    }

    private int leftChild (int index) {
        return index*2 +1;
    }

    private int rightChild (int index) {
        return index*2 + 2;
    }

    private void swap (int childIndex, int parentIndex) {
        Node temp = arr[childIndex];
        arr[childIndex] = arr[parentIndex];
        arr[parentIndex] = temp;
    }

    private boolean isLeafNode (int index) {
        return index >= size/2;
    }

    private void heapify(int index) {
        if (!isLeafNode(index)) {
            if (arr[index].getValue() > arr[leftChild(index)].getValue() || arr[index].getValue() > arr[rightChild(index)].getValue()) {
                if (arr[leftChild(index)].getValue() < arr[rightChild(index)].getValue()) {
                    swap(index, leftChild(index));
                    heapify(leftChild(index));
                } else {
                    swap(index, rightChild(index));
                    heapify(rightChild(index));
                }
            }
        }
    }

    /**
     * Method for getting the amount of nodes in the heap
     * @return the amount
     */
    public int getSize() {
        return size;
    }
}
