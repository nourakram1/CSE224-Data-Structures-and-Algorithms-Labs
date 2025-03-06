package input;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.FileUtils;

public class FileReader {

    public static String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
