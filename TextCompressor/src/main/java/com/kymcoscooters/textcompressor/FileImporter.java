package com.kymcoscooters.textcompressor;

import charMap.CharMap;
import charMap.CharMapEntry;
import minHeap.MinHeap;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileImporter {
    private List<String> lines;
    private List<String> encoded;
    private List<String> decoded;
    private CharMap charIntMap;
    private CharMap charStringMap;
    
    public FileImporter(){
        lines = new ArrayList<>();
        encoded = new ArrayList<>();
        decoded = new ArrayList<>();
        charIntMap = new CharMap();
        charStringMap = new CharMap();
    }
    
    /*
    Method to read from a text file
    */
    public void readText() {
        /*
        File should be in the project root folder
        */
        String fileName = "temp.txt";
        
        String line;
        
        try {
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            /*
            Read lines from the text document
            */
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
                /*
                Read all the characters from the line to check what characters exist and how many
                */
                readChars(line);
            }
            
            bufferedReader.close();
            
            /*
            Min heap using own MinHeap class
            */
            MinHeap heap = new MinHeap();
            
            /*
            Add all the characters to the heap
            */
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
            
            /*
            Print all characters and their respective bytecodes
            */
            printCharAndCode(heap.peek(), "");
            encode();
            System.out.println("Printing all encoded lines");
            for (String s : this.encoded) {
                System.out.println(s);
            }
            System.out.println("Printing all decoded lines");
            decode(heap.peek());
            for (String s : this.decoded) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("unable to open file");
        } catch (IOException e) {
            System.out.println("unable to read file");
        }
    }
    
    /*
    For later use, to read the compressed file and get it back to text
    */
    public void readByteStream() {
        String fileName = "temp.txt";
        
        String line = null;
        
        try {
            byte[] buffer = new byte[1000];
            
            FileInputStream inputStream = new FileInputStream(fileName);
            
            while (inputStream.read() != -1) {
                System.out.println(Arrays.toString(buffer));
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("unable to open file");
        } 
        catch (IOException e) {
            System.out.println("Error reading file");
        }
    }
    
    /*
    Read all the chars on a line, and update the map with the values
    */
    private void readChars(String line) {
        for (Character ch : line.toCharArray()) {
            charIntMap.addChar(ch);
        }
    }
    
    /*
    Recursive method for printing the characters and their codes
    */
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
