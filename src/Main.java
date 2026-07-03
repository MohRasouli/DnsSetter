import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        List<String> liveAdapters = AdapterLister.getAdapterName();
        String userPrimaryDns = "";
        String userSecondaryDns = "";

        System.out.println("!! >> NOTE: Please run this tool as Administrator << !!");
        System.out.println("----------------------------------------");
        for (int i = 0; i < liveAdapters.size(); i++) {
            System.out.println((i + 1) + ". " + liveAdapters.get(i));
        }
        System.out.println("----------------------------------------");


        int userAdapterNumber = -1;
        while (true) {
            System.out.print("Select your adapter (1-" + liveAdapters.size() + "): ");
            String userInput = scanner.nextLine().trim();

            if (userInput.isEmpty()) {
                continue;
            }

            if (userInput.matches("\\d+")) {
                userAdapterNumber = Integer.parseInt(userInput);
                if (userAdapterNumber > 0 && userAdapterNumber <= liveAdapters.size()) {
                    break;
                } else {

                    System.out.println("!! Invalid selection. Please choose a number in range");
                }

            } else {
                System.out.println("!! That wasn't a valid number");
            }

        }

        String userSelectedAdapter = liveAdapters.get(userAdapterNumber - 1);


        while (true) {
            System.out.print("Enter Primary DNS [e.g., 8.8.8.8] (Leave blank to use DHCP): ");

            userPrimaryDns = scanner.nextLine().trim();

            if (userPrimaryDns.isBlank()) {
                System.out.println(">> Reverting to DHCP settings...");
                SetDns.setDns(userSelectedAdapter, userPrimaryDns, userSecondaryDns);
                break;
            }

            if (DnsValidator.isValidDns(userPrimaryDns)) {
                break;
            } else {
                System.out.println("!! Invalid DNS. Use format like 8.8.8.8 with each number 0-255");
            }

        }


        if (!userPrimaryDns.isBlank()) {
            while (true) {
                System.out.print("Secondary DNS (Optional): ");
                userSecondaryDns = scanner.nextLine().trim();

                if (userSecondaryDns.isBlank() || DnsValidator.isValidDns(userSecondaryDns)) {
                    System.out.println(">> Applying DNS...");
                    SetDns.setDns(userSelectedAdapter, userPrimaryDns, userSecondaryDns);
                    break;
                } else {
                    System.out.println("!! Invalid DNS. Use format like 8.8.8.8 with each number 0-255");
                }
            }
        }

        List<String> afterSetData = AdapterInfoReader.adapterInfo(userSelectedAdapter);
        System.out.println("\n*-----------------*");

        for (int i = 0; i < afterSetData.size(); i++) {
            if (afterSetData.get(i).contains("DNS") || i == 0)
                System.out.println(afterSetData.get(i));
        }

        System.out.println("*-----------------*");

        scanner.close();

    }
}

