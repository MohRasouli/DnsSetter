import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterLister {


    public static List<String> getAdapterName() throws IOException, InterruptedException {

        List<String> adaptersData = new ArrayList<>();
        List<String> adapterNames = new ArrayList<>();

        ProcessBuilder getAdapters = new ProcessBuilder("netsh", "interface", "show", "interface");
        Process process = getAdapters.start();
        ProcessOutputReader.readOutput(process, adaptersData);
        process.waitFor();

        for (int i = 2; i < adaptersData.size(); i++) {
            String[] parts = adaptersData.get(i).split("\\s{2,}");
            adapterNames.add(parts[parts.length - 1]);
        }
        return adapterNames;
    }

}
