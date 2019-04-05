import java.io.IOException;

public interface Subscriber extends Node {

    void register(Broker broker, Topic topic);

    void disconnect(Broker broker, Topic topic);

    void getBrokerList();

    void hashTopic();

    void notifyFailure(Broker broker);
}
