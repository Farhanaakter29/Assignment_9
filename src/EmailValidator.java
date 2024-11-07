import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    public static void main(String[] args) {
        String emailFilePath = "emails.txt";  // Path to the file containing the list of email addresses
        String validEmailFilePath = "valid_emails.txt";  // Path to the file to store valid email addresses

        try (BufferedReader reader = new BufferedReader(new FileReader(emailFilePath));
             FileWriter writer = new FileWriter(validEmailFilePath)) {

            String email;
            while ((email = reader.readLine()) != null) {
                if (isValidEmail(email)) {
                    writer.write(email + System.lineSeparator());
                    System.out.println("Valid email: " + email);
                } else {
                    System.out.println("Invalid email: " + email);
                }
            }

            System.out.println("Validation completed. Valid emails written to: " + validEmailFilePath);

        } catch (IOException e) {
            System.out.println("Error reading/writing the file: " + e.getMessage());
        }
    }

    // Method to validate email address using regex
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
