public interface Publisher extends Node {

    void getBrokerList();

    Broker hasTopic(Topic topic);

    void push(Topic topic, Value values);

    void notifyFailure(Broker broker);
}
