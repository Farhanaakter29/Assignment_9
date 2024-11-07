import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonToXmlConverter {

    public static void main(String[] args) {
        String jsonFilePath = "data.json"; // Path to the input JSON file
        String xmlFilePath = "output.xml"; // Path to the output XML file

        try {
            // Step 1: Read the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.readTree(new FileReader(jsonFilePath));

            // Step 2: Convert JSON to XML
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document xmlDocument = dBuilder.newDocument();

            // Assuming the JSON is an object, we start converting it into XML
            Element rootElement = xmlDocument.createElement("root");
            xmlDocument.appendChild(rootElement);

            // Convert JSON nodes to XML elements
            convertJsonToXml(jsonData, rootElement, xmlDocument);

            // Step 3: Write the XML to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(xmlDocument);
            StreamResult result = new StreamResult(new File(xmlFilePath));
            transformer.transform(source, result);

            System.out.println("JSON data successfully converted to XML and written to: " + xmlFilePath);

        } catch (Exception e) {
            System.out.println("Error processing the file: " + e.getMessage());
        }
    }

    // Helper method to convert JSON to XML recursively
    private static void convertJsonToXml(JsonNode jsonNode, Element parentElement, Document xmlDocument) {
        jsonNode.fields().forEachRemaining(entry -> {
            String key = entry.getKey();
            JsonNode value = entry.getValue();

            Element element = xmlDocument.createElement(key);

            if (value.isObject()) {
                convertJsonToXml(value, element, xmlDocument);
            } else if (value.isArray()) {
                value.forEach(item -> {
                    Element itemElement = xmlDocument.createElement(key);
                    if (item.isObject()) {
                        convertJsonToXml(item, itemElement, xmlDocument);
                    } else {
                        itemElement.appendChild(xmlDocument.createTextNode(item.asText()));
                    }
                    parentElement.appendChild(itemElement);
                });
                return; // Skip appending element as array items are already appended
            } else {
                element.appendChild(xmlDocument.createTextNode(value.asText()));
            }

            parentElement.appendChild(element);
        });
    }
}
