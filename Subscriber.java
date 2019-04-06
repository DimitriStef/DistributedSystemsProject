public interface Subscriber extends Node {

    void getBrokerList();

    void hashTopic();

    void notifyFailure(Broker broker);
}
