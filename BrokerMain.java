import java.io.IOException;

public class BrokerMain {

    public static void main(String[] args) throws IOException {
        // create first broker. needs arguments
        Broker masterBroker = new BrokerClass(args);
        masterBroker.init();
        masterBroker.connect();

    }

}
