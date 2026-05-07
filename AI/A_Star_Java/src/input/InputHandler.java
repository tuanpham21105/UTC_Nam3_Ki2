package input;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import model.InputContent;
import model.Node;
import model.RelativeNode;

public class InputHandler {

    public static String readTxtFile(String filePath) {
        try {
            Path path = Path.of(filePath);
            return Files.readString(path);
        }
        catch (Exception e) {
            return "";
        }
    }

    public static InputContent parseInputContent(String inputText) {
        InputContent inputContent = new InputContent();

        String[] lines = inputText.split("\n");

        inputContent.startNodeName = lines[0].trim();
        inputContent.endNodeName = lines[1].trim();
        inputContent.nodes = new Hashtable<>();

        for (int i = 2; i < lines.length; i++) {
            Node node = new Node();

            String line = lines[i];

            String[] lineParts = line.split(":");

            if (lineParts.length == 0)
                continue;

            String[] leftParts = lineParts[0].split("-");

            if (leftParts.length != 2)
                continue;
            
            String nodeName = leftParts[0].trim();
            node.h = Integer.parseInt(leftParts[1].trim());

            if (lineParts.length < 2) {
                inputContent.nodes.put(nodeName, node);
                continue;
            }

            String[] rightParts = lineParts[1].split(",");

            if (rightParts.length == 0) {
                inputContent.nodes.put(nodeName, node);
                continue;
            }

            for (String relativeNodeText : rightParts) {
                String[] parts = relativeNodeText.split("-");

                RelativeNode relativeNode = new RelativeNode();

                relativeNode.nodeName = parts[0].trim();
                relativeNode.k = Integer.parseInt(parts[1].trim());

                node.relativeNodes.add(relativeNode);
            }

            inputContent.nodes.put(nodeName, node);
        }

        return inputContent;
    }
}
