import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LineSeparator {
    public static void main(String[] args) {
        String inputFilePath = "input.txt";  // Replace with the path to your input text file
        String oddLinesFilePath = "odd_lines.txt";  // File for odd-numbered lines
        String evenLinesFilePath = "even_lines.txt";  // File for even-numbered lines

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter oddWriter = new BufferedWriter(new FileWriter(oddLinesFilePath));
             BufferedWriter evenWriter = new BufferedWriter(new FileWriter(evenLinesFilePath))) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (lineNumber % 2 == 0) {
                    evenWriter.write(line);
                    evenWriter.newLine();
                } else {
                    oddWriter.write(line);
                    oddWriter.newLine();
                }
                lineNumber++;
            }
            System.out.println("Even lines written to: " + evenLinesFilePath);
            System.out.println("Odd lines written to: " + oddLinesFilePath);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

