package com.kymcoscooters.textcompressor;

import huffman.Huffman;
import java.util.List;
import textInterface.TextInterface;

public class TextCompressor {
    
    public static void main(String[] args) {
        FileImporter fileImporter = new FileImporter();
        
        TextInterface textInterface = new TextInterface();

        textInterface.start();

        List<String> lines = fileImporter.readText();

        Huffman huffman = new Huffman();

        huffman.build(lines);

        fileImporter.writeByteStream();
    }
}
