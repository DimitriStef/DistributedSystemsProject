public interface Publisher extends Node {

    void getBrokerList();

    Broker hastTopic(Topic topic);

    void push(Topic topic, Value values);

    void notifyFailure(Broker broker);
}
