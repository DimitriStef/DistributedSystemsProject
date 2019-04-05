import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class SubscriberClass implements Subscriber {
    int port = 2534;
    String IP = "127.0.0.1";
    Socket requestSocket = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        new SubscriberClass().connect();
    }


    @Override
    public void connect() {

        try {
            System.out.println("Subscriber connecting to Broker...");
            requestSocket = new Socket(InetAddress.getByName("127.0.0.1"), port);

            while (true) {
                out = new ObjectOutputStream(requestSocket.getOutputStream());
                in = new ObjectInputStream(requestSocket.getInputStream());

                Info broker = (Info) in.readObject();
                System.out.println(" Broker IP :" + broker.getIPaddress());
                System.out.println(broker.getBrokers());
                System.out.println(broker.getIPaddress());
                System.out.println(broker.getBrokerLineIdHash().get(2));
                System.out.println(broker.getPort());

                out.flush();
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void register(Broker broker, Topic topic) {

    }

    @Override
    public void disconnect(Broker broker, Topic topic) {

    }

    @Override
    public void getBrokerList() {

    }

    @Override
    public void hashTopic() {

    }

    @Override
    public void notifyFailure(Broker broker) {

    }

    @Override
    public void init() {

    }


    @Override
    public void disconnect() throws IOException {
        in.close();
        out.close();
        requestSocket.close();
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
