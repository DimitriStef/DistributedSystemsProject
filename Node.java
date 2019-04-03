import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public interface Node {

    ArrayList<Broker> brokers = new ArrayList<Broker>();

    static void init(int port){};

    static void connect() throws IOException{};

    static void disconnect() throws IOException{};

    void updateNodes();

    ArrayList<Broker> getBrokers();

    void setBrokers(ArrayList<Broker> brokers);
}
