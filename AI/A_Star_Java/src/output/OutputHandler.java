package output;

import java.nio.file.Files;
import java.nio.file.Path;

public class OutputHandler {
    public static void writeTxtFile(String filePath, String content) {
        try {
            Path path = Path.of(filePath);
            Files.writeString(path, content);
        }
        catch (Exception e) {
            
        }
    }
}
