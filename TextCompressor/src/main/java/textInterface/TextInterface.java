package textInterface;

import main.FileIO;
import huffman.Huffman;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author holmbrob
 */
public class TextInterface {
    
    /**
     * Starting the program. Loops trough a text interface menu.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        
        FileIO fileIO = new FileIO();

        Huffman huffman = new Huffman(fileIO);
        
        while(true) {
            System.out.println("What would you like to do?\n");
            System.out.println("Compress file, press c");
            System.out.println("Decompress file, press d");
            System.out.println("Exit the program, press e\n");
            switch (scanner.nextLine()) {
                case "c":
                    System.out.println("\ncompress\n");
                    System.out.println("Enter file name:");

                    String fileName = scanner.nextLine();

                    List<String> lines = fileIO.readText(fileName);

                    huffman.build(lines);
                    break;
                case "d":
                    System.out.println("\ndecompress\n");
                    System.out.println("Enter header file name:");

                    String headerFileName = scanner.nextLine();

                    System.out.println("Enter content file name");

                    String contentFileName = scanner.nextLine();

                    huffman.decode(headerFileName, contentFileName);
                    break;
                case "e":
                    System.out.println("\nexiting");
                    return;
                default:
                    System.out.println("\nThat is not a valid input, try again\n");
                    break;
            }
        }
    }
}
