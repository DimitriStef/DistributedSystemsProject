import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class BrokerClass implements Node {

    private ArrayList<Broker> brokers;
    ServerSocket providerSocket;
    Socket connection;
    ObjectOutputStream out;
    ObjectInputStream in;

    public static void main(String[] args) {
        Read_Dataset ds = new Read_Dataset();
        try {
            ds.readDataset();
        } catch (IOException e) {
            e.printStackTrace();
        }
        calculateKeys(args, ds);
    }

    public void init(int port) {
        try {
            providerSocket = new ServerSocket(port);

            while (true) {
                connect();
                disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException {
        connection = providerSocket.accept();
        out = new ObjectOutputStream(connection.getOutputStream());
        in = new ObjectInputStream(connection.getInputStream());

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

    public static void calculateKeys(String[] args, Read_Dataset ds) {
        try {
            byte[] bytes = (args[0] + args[1]).getBytes("UTF-8");
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            for(String toHash : args){
                md5.update(toHash.getBytes("UTF-8"));
            }
            md5.digest(bytes);
            System.out.println(bytes);

            ArrayList<byte[]> hashes = new ArrayList<byte[]>();

            for (int i = 0; i < ds.busLines.size(); i++){
                byte[] msg = ((Read_Dataset.BusLines)(ds.busLines.get(i))).LineID.getBytes("UTF-8");
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] digest = md.digest(msg);
                    hashes.add(digest);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
