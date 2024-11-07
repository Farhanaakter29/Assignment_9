import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileMerger {

    public static void main(String[] args) {
        String firstFilePath = "file1.txt";  // Path to the first input file
        String secondFilePath = "file2.txt"; // Path to the second input file
        String outputFilePath = "merged_output.txt"; // Path to the output merged file

        try (BufferedReader firstFileReader = new BufferedReader(new FileReader(firstFilePath));
             BufferedReader secondFileReader = new BufferedReader(new FileReader(secondFilePath));
             FileWriter outputFileWriter = new FileWriter(outputFilePath)) {

            // Step 1: Read and write from the first file
            String line;
            while ((line = firstFileReader.readLine()) != null) {
                outputFileWriter.write(line + System.lineSeparator());
            }

            // Step 2: Read and write from the second file
            while ((line = secondFileReader.readLine()) != null) {
                outputFileWriter.write(line + System.lineSeparator());
            }

            System.out.println("Files have been merged into: " + outputFilePath);

        } catch (IOException e) {
            System.out.println("Error during file processing: " + e.getMessage());
        }
    }
}


