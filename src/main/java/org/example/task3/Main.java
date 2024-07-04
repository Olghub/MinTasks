package org.example.task3;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Main {

    public static void main(String[] args) {
        String valuesPath = args[0]; //src/main/java/org/example/task3/values.json
        String testsPath = args[1]; //src/main/java/org/example/task3/tests.json
        String reportPath = args[2]; //src/main/java/org/example/task3/report.json

        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonNode valuesRoot = mapper.readTree(new File(valuesPath)).get("values");
            JsonNode testsRoot = mapper.readTree(new File(testsPath)).get("tests");
            fillValues(testsRoot, valuesRoot);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportPath), testsRoot);
        } catch (IOException e) {
            System.out.println("Произошла ошибка");
        }
    }

    private static void fillValues(JsonNode testsRoot, JsonNode valuesRoot) {
        if (testsRoot.isObject()) {
            ObjectNode objectNode = (ObjectNode) testsRoot;
            if (objectNode.has("value")) {
                int id = objectNode.get("id").asInt();
                String value = findValueById(valuesRoot, id);
                objectNode.put("value", value);
            }

            if (objectNode.has("values")) {
                JsonNode valuesNode = objectNode.get("values");
                if (valuesNode.isArray()) {
                    Iterator<JsonNode> elements = valuesNode.elements();
                    while (elements.hasNext()) {
                        fillValues(elements.next(), valuesRoot);
                    }
                }
            }
        } else if (testsRoot.isArray()) {
            Iterator<JsonNode> elements = testsRoot.elements();
            while (elements.hasNext()) {
                fillValues(elements.next(), valuesRoot);
            }
        }
    }

    private static String findValueById(JsonNode valuesRoot, int id) {
        Iterator<JsonNode> elements = valuesRoot.elements();
        while (elements.hasNext()) {
            JsonNode element = elements.next();
            if (element.get("id").asInt() == id) {
                return element.get("value").asText();
            }
        }

        return null;
    }
}
