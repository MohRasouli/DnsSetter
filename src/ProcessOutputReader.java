import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ProcessOutputReader {

    public static void readOutput(Process process, List<String> dataList) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    dataList.add(line);
                }
            }
        }
    }
}
