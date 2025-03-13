package input;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReader {

    public static String readFile(String filePath) {
        File file = new File(filePath);
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Error reading file");
            return null;
        }
    }
}
