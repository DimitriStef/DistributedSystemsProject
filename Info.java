import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
//Info {ListOfBrokers {IP,Port} , < BrokerId,ResponsibilityLine>}.


public class Info implements Serializable {
    private ArrayList<Broker> brokers;
    private ArrayList<byte[]> brokerLineIdHash;
    private String IPaddress;
    private int port;

    //private HashMap<Broker,Info> brokerInfoHashMap;


    public Info(ArrayList<Broker> brokers, ArrayList<byte[]> brokerLineIdHash, String IPaddress, int port) {
        this.brokers = brokers;
        this.brokerLineIdHash = brokerLineIdHash;
        this.IPaddress = IPaddress;
        this.port = port;
    }

    public ArrayList<byte[]> getBrokerLineIdHash() {
        return brokerLineIdHash;
    }

    public String getIPaddress() {
        return IPaddress;
    }

    public int getPort() {
        return port;
    }

    public ArrayList<Broker> getBrokers() {
        return brokers;
    }
}
