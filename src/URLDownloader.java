import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class URLDownloader {

    public static void main(String[] args) {
        String urlFilePath = "urls.txt";  // Path to the file containing the list of URLs

        try (BufferedReader reader = new BufferedReader(new FileReader(urlFilePath))) {
            String urlString;
            int urlIndex = 1;

            while ((urlString = reader.readLine()) != null) {
                try {
                    // Download content from the URL
                    downloadContentFromURL(urlString, "content_" + urlIndex + ".html");
                    System.out.println("Downloaded content from: " + urlString);
                    urlIndex++;
                } catch (MalformedURLException e) {
                    System.out.println("Invalid URL: " + urlString);
                } catch (IOException e) {
                    System.out.println("Error downloading content from: " + urlString + " - " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the URL file: " + e.getMessage());
        }
    }

    // Method to download content from a URL and save it to a file
    private static void downloadContentFromURL(String urlString, String outputFilePath) throws IOException {
        URL url = new URL(urlString);
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(outputFilePath));
        }
    }
}

