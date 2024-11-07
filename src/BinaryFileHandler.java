import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class BinaryFileHandler {

    public static void main(String[] args) {
        String inputFilePath = "input.bin";  // Replace with the path to your binary input file
        String outputFilePath = "output.bin";  // Replace with the path to your binary output file

        try {
            // Step 1: Read from the input binary file and write to the output binary file
            FileInputStream inputStream = new FileInputStream(inputFilePath);
            FileOutputStream outputStream = new FileOutputStream(outputFilePath);

            int byteData;
            while ((byteData = inputStream.read()) != -1) {
                outputStream.write(byteData);
            }

            inputStream.close();
            outputStream.close();

            System.out.println("File copied successfully.");

            // Step 2: Ask the user if they want to append data to the file
            appendData(outputFilePath);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to append data to the binary file
    public static void appendData(String filePath) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Do you want to append data to the file? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            System.out.print("Enter the data to append (as a string): ");
            String data = scanner.nextLine();

            try (FileOutputStream outputStream = new FileOutputStream(filePath, true)) {
                // Convert string to bytes and append to the file
                outputStream.write(data.getBytes());
                System.out.println("Data appended successfully.");
            } catch (IOException e) {
                System.out.println("Error appending data: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

