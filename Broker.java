import java.io.Serializable;
import java.util.ArrayList;

public interface Broker extends Node{

    ArrayList<Subscriber> registeredSubscribers = new ArrayList<Subscriber>();
    ArrayList<Publisher> registeredPublishers = new ArrayList<Publisher>();

    Publisher acceptConnection(Publisher pub);

    Subscriber acceptConnection(Subscriber sub);

    void notifyPublishers(String str);

    void pull(Topic topic);
}
