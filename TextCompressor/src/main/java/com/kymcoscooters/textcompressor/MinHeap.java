package com.kymcoscooters.textcompressor;

public class MinHeap {
    private Node[] arr;
    private int size;

    public MinHeap() {
        arr = new Node[1000];
        size = 0;
    }

    public void push(Node node) {
        arr[size] = node;
        int current = size;
        this.size++;
        while (arr[current].getValue() < arr[parent(current)].getValue()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public Node pop() {
        Node popped = arr[0];
        arr[0] = arr[size-1];
        size--;
        heapify(0);
        return popped;
    }

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

    public int getSize() {
        return size;
    }
}
