package com.kymcoscooters.textcompressor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FileImporter {
    private HashMap<Character, Integer> map;
    
    public FileImporter(){
        map = new HashMap<>();
    }
    
    /*
    Method to read from a text file
    */
    public void readText() {
        /*
        File should be in the project root folder
        */
        String fileName = "temp2.txt";
        
        String line = null;
        
        try {
            FileReader fileReader = new FileReader(fileName);
            
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            /*
            Read lines from the text document
            */
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                /*
                Read all the characters from the line
                */
                readChars(line);
            }
            
            bufferedReader.close();
            
            System.out.println("---------------------------------------------------------");
            
            /*
            PriorityQueue is a Java library for using a min heap
            */
            PriorityQueue<Node> queue = new PriorityQueue<Node>();
            
            /*
            Add all the characters to the heap
            */
            for (Map.Entry<Character, Integer> entry : this.map.entrySet()) {
                Node node = new Node(entry.getKey(), entry.getValue());
                queue.add(node);
            }
            
            while (queue.size() > 1) {
                /*
                Take the charactes with the smallest values and remove them from the heap
                */
                Node node1 = queue.poll();
                Node node2 = queue.poll();
                
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
                queue.add(newNode);
            }
            
            /*
            Print all characters and their respective bytecodes
            */
            printCharAndCode(queue.peek(), "");
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
    Read all the chars on a line, and update the hashmap with the values
    */
    private void readChars(String line) {
        for (Character ch : line.toCharArray()) {
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                map.put(ch, map.get(ch) + 1);
            }
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
}
