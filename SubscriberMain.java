public class SubscriberMain {

     public static void main(String[] args) {
         //proswrino
         ReadDataset rd = new ReadDataset();
         SubscriberClass  sb = new SubscriberClass();

         sb.connect();

         System.out.println("Subscriber registered at Topic :"+rd.getBusLines().get(0));
         sb.register(sb.getBrokers().get(0),rd.getBusLines().get(0));
    }
}
