import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class BrokerClass implements Broker, Serializable {

    private ArrayList<Broker> brokers = new ArrayList<>();
    private transient ServerSocket providerSocket = null;
    private transient Socket connection = null;
    private transient ObjectOutputStream out = null;
    private transient ObjectInputStream in = null;
    private ArrayList<byte[]> brokerLineIdHash = new ArrayList<>();
    private String[] args;
    private String IPaddress;
    private int port;
    private byte[] brokerIPHash;

    // constructor, writes IP and Port of broker object
    public BrokerClass(String[] args) {
        this.args = args;
        IPaddress = args[0];
        port = Integer.parseInt(args[1]);
        brokers.add(this);
    }

    // read dataset, create hashes, create providerSocket
    public void init() {
        ReadDataset ds = new ReadDataset();

        brokerIPHash = calculateKeys(IPaddress,port);
        ArrayList<byte[]> lineIdHash = calculateKeys(ds);

        // compare hashes, "debugging" purposes
        for (int i = 0; i < lineIdHash.size(); i++) {
            if (brokerIPHash.toString().compareTo(lineIdHash.get(i).toString()) == 1) {
                System.out.println("Broker Key :"+lineIdHash.get(i));
                brokerLineIdHash.add(lineIdHash.get(i));
            }
        }

        // create providerSocket
        try {
            providerSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // try connection with client, read and return Node if accepted
    public void connect() {

        try {
           /* // if connected, accept pub or sub object casted as Node
            Node node = (Node) in.readObject();
            if (node instanceof Publisher)
                out.writeObject(acceptConnection((Publisher) node));
            else if (node instanceof Subscriber)
                out.writeObject(acceptConnection((Subscriber) node));*/

            System.out.println("Accepting connection from Subscriber...");

            while (true) {
                connection = providerSocket.accept();
                out = new ObjectOutputStream(connection.getOutputStream());
                //in = new ObjectInputStream(connection.getInputStream());

                sendInfoToSubscribers();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            try {
                disconnect();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    //send informations about brokers to Subscribers
    private void sendInfoToSubscribers() throws IOException {
        out.writeObject(new Info(brokers,brokerLineIdHash,IPaddress,port));
        System.out.println("Info accepted by subscriber...");
    }

    public void disconnect() throws IOException {
        in.close();
        out.close();
        connection.close();
    }

    public void updateNodes() {
    }

    public ArrayList<Broker> getBrokers() {
        return brokers;
    }

    public void setBrokers(ArrayList<Broker> brokers) {
        this.brokers = brokers;
    }

    public byte[] calculateKeys(String IPaddress, int port) {
        try {
            byte[] IPandPortBytes = (IPaddress + port).getBytes("UTF-8");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return md5.digest(IPandPortBytes);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<byte[]> calculateKeys(ReadDataset ds) {
        ArrayList<byte[]> hashes = new ArrayList<byte[]>();
        byte[] lineIDBytes = new byte[0];
        for (int i = 0; i < ds.getBusLines().size(); i++) {
            try {
                lineIDBytes = ((ReadDataset.BusLine) (ds.getBusLines().get(i))).getLineID().getBytes("UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] lineIDHash = md.digest(lineIDBytes);
                hashes.add(lineIDHash);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return hashes;
    }

    public Publisher acceptConnection(Publisher pub) {
        return pub;
    }

    public Subscriber acceptConnection(Subscriber sub) {
        return sub;
    }

    public void notifyPublishers(String str) {

    }

    public void pull(Topic topic) {

    }

    public String getIPaddress() {
        return IPaddress;
    }

    public ArrayList<byte[]> getBrokerLineIdHash() {
        return brokerLineIdHash;
    }

}
