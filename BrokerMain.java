import java.io.IOException;

public class BrokerMain {

    public static void main(String[] args) {

        // create first broker. needs arguments
        BrokerClass masterBroker = new BrokerClass(args);
        masterBroker.init();

        while (true) {
            try {
                masterBroker.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
