import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;



public class PublisherClass implements Publisher{



    @Override
    public void getBrokerList() {

    }

    @Override
    public Broker hasTopic(Topic topic) {
        return null;
    }

    @Override
    public void push(Topic topic, Value values) {

    }

    @Override
    public void notifyFailure(Broker broker) {

    }

    @Override
    public void init() {

    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public void disconnect() throws IOException {

    }

    @Override
    public void updateNodes() {

    }

    @Override
    public ArrayList<Broker> getBrokers() {
        return null;
    }

    @Override
    public void setBrokers(ArrayList<Broker> brokers) {

    }
}
