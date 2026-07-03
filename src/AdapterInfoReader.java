import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterInfoReader {

    public static List<String> adapterInfo(String adapterName) throws IOException, InterruptedException {

        List<String> adapterInfo = new ArrayList<>();

        ProcessBuilder getAdapterInfo = new ProcessBuilder("netsh", "interface", "ipv4", "show", "config", "name=" + adapterName);
        Process process = getAdapterInfo.start();
        ProcessOutputReader.readOutput(process, adapterInfo);
        process.waitFor();

        return adapterInfo;
    }
}
