package main;

import charMap.CharMap;
import charMap.CharMapEntry;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author holmbrob
 */
public class FileIO {

    /**
     *
     */
    public FileIO(){
    }

    /**
     * Method for reading text from a file
     * @param fileName, the name of the file that is being read.
     * @return a list of strings found in the file that was read.
     */

    public List<String> readText(String fileName) {
        /*
        File should be in the project root folder
        */
        
        String line;
        ArrayList<String> lines = new ArrayList<>();
        
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
            
            return lines;
        } catch (FileNotFoundException e) {
            System.out.println("unable to open file");
        } catch (IOException e) {
            System.out.println("unable to read file");
        }
        return null;
    }

    /**
     * Method for reading compressed bytes from a compressed file
     * @param fileName, name of the file that should be read from.
     * @return a list of strings of 1s and 0s
     */
    public List<String> readByteStream(String fileName) {
        try {
            byte[] buffer = new byte[1000];
            
            FileInputStream inputStream = new FileInputStream(fileName);
            
            String help = "";

            int i = inputStream.read();

            while (i != -1) {
                help += String.format("%8s", Integer.toBinaryString((i + 256) % 256)).replace(' ', '0');

                i = inputStream.read();
            }

            System.out.println("bytestreamhelp: " + help);

            List<String> list = new ArrayList<>();
            list.add(help);
            return list;
        } 
        catch (FileNotFoundException e) {
            System.out.println("unable to open file");
        } 
        catch (IOException e) {
            System.out.println("Error reading file");
        }
        return null;
    }
    
    /**
     * Method for writing the compressed text into a file
     * @param lines, the strings of 1s and 0s that should be written into the file
     * @param map, the mapping of characters and their amount in the text, to be written into the header file
     */
    public void writeCompressed(List<String> lines, CharMap map) {
        try {
            System.out.println("Enter preferred filename of header file:");

            Scanner scanner = new Scanner(System.in);

            String headerFileName = scanner.nextLine();

            System.out.println("Enter preferred filename of content file:");

            String contentFileName = scanner.nextLine();

            PrintWriter pw = new PrintWriter(headerFileName);

            for (CharMapEntry e : map.getArr()) {
                try {
                    e.getCh();
                }
                catch (NullPointerException ex) {
                    break;
                }
                pw.write(e.getCh());
                pw.write(e.getAmount().toString());
            }
            pw.close();

            FileOutputStream fout = new FileOutputStream(contentFileName);
            BufferedOutputStream bout = new BufferedOutputStream(fout);

            for (String line : lines) {
                int byteAmount = line.length()/8;
                byte[] arr = new byte[byteAmount + 1];
                int index = 0;
                int start = 0;
                int end = 8;
                while (true) {
                    boolean toBreak = false;
                    if (end > line.length()) {
                        end = line.length();
                        toBreak = true;
                    }
                    String byteString = line.substring(start, end);
                    while (byteString.length() < 8) {
                        byteString += '0';
                    }
                    byte asd = (byte)(int)Integer.valueOf(byteString, 2);
                    arr[index] = asd;
                    index++;
                    start += 8;
                    end += 8;
                    if (toBreak) {
                        break;
                    }
                }

                bout.write(arr, 0, byteAmount + 1);
            }

            bout.flush();
            fout.close();
        }
        catch (IOException e) {
            System.out.println("file not found");
        }
    }
}
