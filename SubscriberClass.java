import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class SubscriberClass implements Subscriber {
    int port = 2534;
    String IP = "127.0.0.1";
    Socket requestSocket = null;
    ObjectOutputStream out = null;
    ObjectInputStream in = null;
    private ArrayList<Broker> brokers;

    @Override
    public void connect() {

        try {
            System.out.println("Subscriber connecting to Broker...");
            requestSocket = new Socket(InetAddress.getByName("127.0.0.1"), port);

            out = new ObjectOutputStream(requestSocket.getOutputStream());
            in = new ObjectInputStream(requestSocket.getInputStream());
            Info brokerInfo;
            brokerInfo = (Info) in.readObject();

            //BrokerClass brokers = (BrokerClass) brokerInfo.getBrokers().get(0);
            setBrokers(brokerInfo.getBrokers());
            System.out.println("BrokerInfo getBrokerLineIdHash :" + brokerInfo.getBrokerLineIdHash()+"\n");
            out.flush();
            disconnect();

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
    public void register(Broker broker, ReadDataset.BusLine busLine)  {
        //ta IP/port einai proswrina, kanonika broker.IP/broker.port
        try {
            System.out.println("Subscriber registering to Broker...\n");
            requestSocket = new Socket(InetAddress.getByName(IP), port);

            while (true) {
                in = new ObjectInputStream(requestSocket.getInputStream());
                Info brokerInfo = (Info) in.readObject();
                System.out.println("Subscriber registered to broker \n");

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
    public void unregister(Broker broker, ReadDataset.BusLine busLine) {

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
    public  ArrayList<Broker> getBrokers() {
        return brokers;
    }

    @Override
    public void setBrokers(ArrayList<Broker> brokers) {
        this.brokers = brokers;
    }
}
