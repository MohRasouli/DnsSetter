import java.io.IOException;

public class SetDns {

    public static void setDns(String adapterName, String primaryDns, String secondaryDns) throws IOException, InterruptedException {
        if (primaryDns.isBlank()) {

            ProcessBuilder pb = new ProcessBuilder("netsh", "interface", "ipv4", "set", "dns",
                    "name=" + adapterName, "dhcp");
            pb.start().waitFor();
            System.out.println(">> DNS reverted to DHCP (Default)");

        } else {

            new ProcessBuilder("netsh", "interface", "ipv4", "set", "dns",
                    "name=" + adapterName, "static", primaryDns).start().waitFor();

            if (!secondaryDns.isBlank()) {
                new ProcessBuilder("netsh", "interface", "ipv4", "add", "dns",
                        "name=" + adapterName, secondaryDns, "index=2").start().waitFor();

            }
            System.out.println(">> DNS applied");
        }


    }
}
