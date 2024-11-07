import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class ConfigManager {

    private static final String configFilePath = "config.properties";  // Path to properties file
    private static Properties properties = new Properties();

    public static void main(String[] args) {
        try {
            // Load the properties from the file
            FileInputStream inputStream = new FileInputStream(configFilePath);
            properties.load(inputStream);
            inputStream.close();

            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n--- Configuration Manager ---");
                System.out.println("1. View Configuration");
                System.out.println("2. Update Configuration");
                System.out.println("3. Save and Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();  // consume the newline

                switch (choice) {
                    case 1:
                        viewConfig();
                        break;
                    case 2:
                        updateConfig(scanner);
                        break;
                    case 3:
                        saveConfig();
                        System.out.println("Configuration saved. Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }

            } while (choice != 3);

            scanner.close();

        } catch (IOException e) {
            System.out.println("Error reading properties file: " + e.getMessage());
        }
    }

    // View the current configuration
    private static void viewConfig() {
        System.out.println("\nCurrent Configuration:");
        for (String key : properties.stringPropertyNames()) {
            String value = properties.getProperty(key);
            System.out.println(key + " = " + value);
        }
    }

    // Update the configuration
    private static void updateConfig(Scanner scanner) {
        System.out.print("Enter the key you want to update: ");
        String key = scanner.nextLine();

        if (properties.containsKey(key)) {
            System.out.print("Enter the new value for '" + key + "': ");
            String newValue = scanner.nextLine();
            properties.setProperty(key, newValue);
            System.out.println("Configuration updated.");
        } else {
            System.out.println("Key '" + key + "' not found.");
        }
    }

    // Save the updated configuration back to the file
    private static void saveConfig() {
        try (FileOutputStream outputStream = new FileOutputStream(configFilePath)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            System.out.println("Error saving properties file: " + e.getMessage());
        }
    }
}
