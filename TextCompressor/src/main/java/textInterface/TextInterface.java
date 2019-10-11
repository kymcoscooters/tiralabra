package textInterface;

import com.kymcoscooters.textcompressor.FileImporter;
import java.util.Scanner;

public class TextInterface {
    
    public void start() {
        Scanner scanner = new Scanner(System.in);
        
        FileImporter fileImporter = new FileImporter();
        
        while(true) {
            System.out.println("What would you like to do?\n");
            System.out.println("Compress file, press c");
            System.out.println("Decompress file, press d");
            System.out.println("Exit the program, press e\n");
            switch (scanner.nextLine()) {
                case "c":
                    System.out.println("\ncompress\n");
                    break;
                case "d":
                    System.out.println("\ndecompress\n");
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
