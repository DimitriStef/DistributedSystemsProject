import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class BrokerClass implements Broker {

    private ArrayList<Broker> brokers;
    private ServerSocket providerSocket;
    private Socket connection;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String[] args;

    // constructor, writes IP and Port of broker object
    public BrokerClass(String[] args) {
        this.args = args;
    }

    // read dataset, create hashes, create providerSocket
    public void init() {
        Read_Dataset ds = new Read_Dataset();
        try {
            ds.readDataset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] ipHash = calculateKeys(args);
        ArrayList<byte[]> lineIdHash = calculateKeys(ds);

        // compare hashes, "debugging" purposes
        for (int i = 0; i < lineIdHash.size(); i++) {
            if (ipHash.toString().compareTo(lineIdHash.get(i).toString()) == 1)
                System.out.println(lineIdHash.get(i));
        }

        // create providerSocket
        try {
            providerSocket = new ServerSocket(Integer.parseInt(args[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // try connection with client, read and return Node if accepted
    public void connect() throws IOException {
        connection = providerSocket.accept();
        out = new ObjectOutputStream(connection.getOutputStream());
        in = new ObjectInputStream(connection.getInputStream());

        try {
            // if connected, accept pub or sub object casted as Node
            Node node = (Node) in.readObject();
            if (node instanceof Publisher)
                out.writeObject(acceptConnection((Publisher) node));
            else if (node instanceof Subscriber)
                out.writeObject(acceptConnection((Subscriber) node));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        out.writeObject("Connection successful");
        out.flush();
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

    public byte[] calculateKeys(String[] args) {
        try {
            byte[] IPandPortBytes = (args[0] + args[1]).getBytes("UTF-8");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(IPandPortBytes);
            return md5.digest(IPandPortBytes);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<byte[]> calculateKeys(Read_Dataset ds) {
        ArrayList<byte[]> hashes = new ArrayList<byte[]>();

        byte[] lineIDBytes;
        for (int i = 0; i < ds.busLines.size(); i++) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                lineIDBytes = ((Read_Dataset.BusLines) (ds.busLines.get(i))).LineID.getBytes("UTF-8");
                md.update(lineIDBytes);
                hashes.add(md.digest(lineIDBytes));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
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
}
