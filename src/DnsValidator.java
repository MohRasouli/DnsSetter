public class DnsValidator {

    public static boolean isValidDns(String dns) {

        String[] parts = dns.split("\\.");

        if (!dns.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            return false;
        }
        for (String part : parts) {
            if (Integer.parseInt(part) > 255 || Integer.parseInt(part) < 0) {
                return false;
            }

        }
        return true;
    }
}
