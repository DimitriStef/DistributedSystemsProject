import java.io.IOException;

public interface Subscriber extends Node {

    void register(Broker broker, ReadDataset.BusLine busLine);

    void unregister(Broker broker, ReadDataset.BusLine busLine);

    void notifyFailure(Broker broker);
}
