import java.util.ArrayList;

public interface Broker extends Node {

    ArrayList<Subscriber> registeredSubscribers = new ArrayList<Subscriber>();
    ArrayList<Publisher> registeredPublishers = new ArrayList<Publisher>();

    static Publisher acceptConnection(Publisher pub){return pub;};

    static Subscriber acceptConnection(Subscriber sub){return sub;};

    void notifyPublishers(String str);

    void pull(Topic topic);
}
