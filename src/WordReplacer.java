import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WordReplacer {

    public static void main(String[] args) {
        String filePath = "textfile.txt";  // Replace with the path to your text file

        try {
            // Step 1: Read the original file content
            StringBuilder fileContent = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent.append(line).append(System.lineSeparator());
            }
            reader.close();

            // Step 2: Ask the user for the word to replace and the new word
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the word to replace: ");
            String wordToReplace = scanner.nextLine();
            System.out.print("Enter the new word: ");
            String newWord = scanner.nextLine();

            // Step 3: Replace the word in the file content
            String modifiedContent = fileContent.toString().replace(wordToReplace, newWord);

            // Step 4: Write the modified content back to the file
            PrintWriter writer = new PrintWriter(new FileWriter(filePath));
            writer.write(modifiedContent);
            writer.close();

            System.out.println("Word replacement completed successfully.");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

