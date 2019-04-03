import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public interface Node {

    ArrayList<Broker> brokers = new ArrayList<Broker>();

    void init(int port);

    void connect() throws IOException;

    void disconnect() throws IOException;

    void updateNodes();

    ArrayList<Broker> getBrokers();

    void setBrokers(ArrayList<Broker> brokers);
}
