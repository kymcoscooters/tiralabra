package huffman;

import charMap.CharMap;
import charMap.CharMapEntry;
import java.util.ArrayList;
import java.util.List;
import minHeap.MinHeap;

public class Huffman {
    
    private List<String> lines;
    private List<String> encoded;
    private List<String> decoded;
    private CharMap charIntMap;
    private CharMap charStringMap;
    private MinHeap heap;
    
    public Huffman() {
        this.lines = new ArrayList<>();
        this.encoded = new ArrayList<>();
        this.decoded = new ArrayList<>();
        this.charIntMap = new CharMap();
        this.charStringMap = new CharMap();
        this.heap = new MinHeap();
    }
    
    public void build (List<String> lines) {
        this.lines = lines;
        
        for (String line : lines) {
            readChars(line);
            System.out.println(line);
        }
        
        buildHeap();
        printCharAndCode(heap.peek(), "");
        encode();
        decode(heap.peek());
        for (String s : this.decoded) {
            System.out.println(s);
        }
    }
    
    private void readChars(String line) {
        for (Character ch : line.toCharArray()) {
            charIntMap.addChar(ch);
        }
    }
    
    private void buildHeap() {
        CharMapEntry[] array = charIntMap.getArr();
        for (int i = 0; i < charIntMap.getSize(); i++) {
            Node node = new Node(array[i].getCh(), array[i].getAmount());
            heap.push(node);
        }
    
        while (heap.getSize() > 1) {
            /*
            Take the charactes with the smallest values and remove them from the heap
            */
            Node node1 = heap.pop();
            Node node2 = heap.pop();

            /*
            Create a new node with a value that is the sum of the two nodes
            */
            int sum = node1.getValue() + node2.getValue();

            Node newNode = new Node(sum);

            newNode.setLeft(node1);
            newNode.setRight(node2);

            /*
            Add it to the heap
            */
            heap.push(newNode);
        }
    }
    
    private void printCharAndCode(Node node, String string) {
        /*
        End of recursion, print the character and the whole code
        */
        if (node.getLeft() == null && node.getRight() == null) {
            System.out.println(node.getCh() + " : " + string);
            /*
            Putting all characters to a list with their respective binary string representation.
            To be changed later to some cleaner way to handle this.
            */
            charStringMap.addChar(node.getCh(), string);
            return;
        }
        
        /*
        Traverse the tree to the left, adding a 0 to the code
        */
        printCharAndCode(node.getLeft(), string + "0");
        /*
        Traverse the tree to the right, adding a 1 to the code
        */
        printCharAndCode(node.getRight(), string + "1");
    }
    
    private void encode() {
        for (String s : lines) {
            String string = "";
            for (char c : s.toCharArray()) {
                String code = charStringMap.getString(c);
                string += code;
            }
            this.encoded.add(string);
        }
    }
    
    private void decode (Node root) {
        for (String s : encoded) {
            String string = "";
            Node node = root;
            for (char c : s.toCharArray()) {
                if (c == '0') {
                    node = node.getLeft();
                }
                if (c == '1') {
                    node = node.getRight();
                }
                if (node.getCh() != null) {
                    string += node.getCh();
                    node = root;
                }
            }
            this.decoded.add(string);
        }
    }
}
