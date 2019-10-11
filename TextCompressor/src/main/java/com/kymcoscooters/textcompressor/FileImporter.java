package com.kymcoscooters.textcompressor;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileImporter {
    private List<String> lines;

    public FileImporter(){
        lines = new ArrayList<>();
    }

    /*
    Method to read from a text file
    */
    public List<String> readText() {
        /*
        File should be in the project root folder
        */
        String fileName = "temp2.txt";
        
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
            }
            
            bufferedReader.close();
            
            // Huffman huffman = new Huffman();
            
            // huffman.build(lines);
            
            return lines;
        } catch (FileNotFoundException e) {
            System.out.println("unable to open file");
        } catch (IOException e) {
            System.out.println("unable to read file");
        }
        return null;
    }
    
    /*
    For later use, to read the compressed file and get it back to text
    */
    public void readByteStream() {
        String fileName = "test.data";
        
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
    
    public void writeByteStream() {
        try {
            byte[] arr = new byte[10];
            arr[0] = 'a';
            arr[1] = 'b';
            arr[2] = 'c';
            arr[3] = 'd';
            arr[4] = 'e';
            arr[5] = 'f';
            arr[6] = 'g';
            arr[7] = 'h';
            arr[8] = 'i';
            arr[9] = 'j';

            FileOutputStream fout = new FileOutputStream("test.data");
            BufferedOutputStream bout = new BufferedOutputStream(fout);
            bout.write(arr, 0, 10);
            bout.flush();
            fout.close();
        }
        catch (IOException e) {
            System.out.println("Error writing file");
        }
    }
}
