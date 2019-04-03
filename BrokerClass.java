import javax.xml.bind.DatatypeConverter;
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

    static private ArrayList<Broker> brokers;
    static ServerSocket providerSocket;
    static Socket connection;
    static ObjectOutputStream out;
    static ObjectInputStream in;

    public static void main(String[] args) {

        init(args);
    }

    public static void init(String[] args) {
        Read_Dataset ds = new Read_Dataset();
        try {
            ds.readDataset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] ipHash = calculateKeys(args);
        ArrayList<byte[]> lineIdHash = calculateKeys(ds);

        // compare hashes
        for (int i = 0; i < lineIdHash.size(); i++) {
            if (DatatypeConverter.printHexBinary(ipHash).compareTo(DatatypeConverter.printHexBinary(lineIdHash.get(i))) == 1)
                System.out.println(lineIdHash.get(i));
        }

        try {
            providerSocket = new ServerSocket(Integer.parseInt(args[1]));

            while (true) {
                connect();
                disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws IOException {
        connection = providerSocket.accept();
        out = new ObjectOutputStream(connection.getOutputStream());
        in = new ObjectInputStream(connection.getInputStream());

        try {
            Object node = in.readObject();
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

    public static void disconnect() throws IOException {
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

    public static byte[] calculateKeys(String[] args) {
        try {
            byte[] bytes = (args[0] + args[1]).getBytes("UTF-8");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            for (String toHash : args) {
                md5.update(toHash.getBytes("UTF-8"));
            }
            md5.digest(bytes);

            return bytes;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<byte[]> calculateKeys(Read_Dataset ds) {
        ArrayList<byte[]> hashes = new ArrayList<byte[]>();

        for (int i = 0; i < ds.busLines.size(); i++) {
            byte[] msg = new byte[0];
            try {
                msg = ((Read_Dataset.BusLines) (ds.busLines.get(i))).LineID.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] bytes = md.digest(msg);
                hashes.add(bytes);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return hashes;
    }

    public static Publisher acceptConnection(Publisher pub) {
        return pub;
    }

    public static Subscriber acceptConnection(Subscriber sub) {
        return sub;
    }

    public void notifyPublishers(String str) {

    }

    public void pull(Topic topic) {

    }
}
