package huffman;

import charMap.CharMap;
import charMap.CharMapEntry;
import main.FileIO;
import java.util.ArrayList;
import java.util.List;
import minHeap.MinHeap;

/**
 *
 * @author holmbrob
 */
public class Huffman {
    
    private FileIO fileIO;
    
    /**
     * Constructor for the class
     * @param fileIO, FileIO object, used for reading from files
     */
    public Huffman(FileIO fileIO) {
        this.fileIO = fileIO;
    }

    /**
     * Method for building the huffman tree
     * @param lines, the strings that should be compressed
     */
    public void build (List<String> lines) {
        
        CharMap charIntMap = readChars(lines);
        
        for (CharMapEntry e : charIntMap.getArr()) {
            try {
                e.getCh();
            }
            catch (NullPointerException ex) {
                break;
            }
            System.out.println("char: " + e.getCh() + " amount: " + e.getAmount());
        }

        MinHeap heap = buildHeap(charIntMap);

        CharMap charStringMap = printCharAndCode(heap.peek(), "", new CharMap());

        encode(lines, charIntMap, charStringMap);
    }
    
    private CharMap readChars(List<String> lines) {
        CharMap map = new CharMap();
        for (String line : lines) {
            for (Character ch : line.toCharArray()) {
                map.addChar(ch);
            }
        }

        return map;
    }
    
    private MinHeap buildHeap(CharMap map) {
        CharMapEntry[] array = map.getArr();
        MinHeap heap = new MinHeap();
        for (int i = 0; i < map.getSize(); i++) {
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
        return heap;
    }
    
    private CharMap printCharAndCode(Node node, String string, CharMap map) {
        /*
        End of recursion, print the character and the whole code
        */
        if (node.getLeft() == null && node.getRight() == null) {
            System.out.println(node.getCh() + " : " + string);
            /*
            Putting all characters to a map with their respective binary string representation.
            */
            map.addChar(node.getCh(), string);
            return map;
        }
        
        /*
        Traverse the tree to the left, adding a 0 to the code
        */
        printCharAndCode(node.getLeft(), string + "0", map);
        /*
        Traverse the tree to the right, adding a 1 to the code
        */
        printCharAndCode(node.getRight(), string + "1", map);

        return map;
    }
    
    private void encode(List<String> lines, CharMap charIntMap, CharMap charStringMap) {
        List<String> encoded = new ArrayList<>();
        for (String s : lines) {
            String string = "";
            for (char c : s.toCharArray()) {
                String code = charStringMap.getString(c);
                string += code;
            }
            encoded.add(string);
        }
        this.fileIO.writeCompressed(encoded, charIntMap);
    }
    
    /**
     * Method for decoding compressed text
     * @param headerFileName, filename of the header file
     * @param contentFileName, filename of the content file
     */
    public void decode(String headerFileName, String contentFileName) {
        String header = this.fileIO.readText(headerFileName).get(0);
        List<String>  content = this.fileIO.readByteStream(contentFileName);
        List<String> decoded = new ArrayList<>();
        CharMap map = buildMap(header);
        MinHeap heap = buildHeap(map);
        Node root = heap.peek();
        for (String s : content) {
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
            decoded.add(string);
        }
        for (String s : decoded) {
            System.out.println(s);
        }
    }

    private CharMap buildMap(String s) {
        CharMap map = new CharMap();
        String help = "";
        CharMapEntry entry = new CharMapEntry();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                help += Character.toString(c);
            } else {
                if (entry.getCh() == null) {
                    entry.setCh(c);
                } else {
                    entry.setAmount(Integer.parseInt(help));
                    help = "";
                    map.addEntry(entry);
                    entry = new CharMapEntry();
                    entry.setCh(c);
                }
            }
        }
        entry.setAmount(Integer.parseInt(help));
        map.addEntry(entry);
        return map;
    }
}
