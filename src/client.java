import java.io.IOException;
import java.net.*;

public class client {
    public static void main(String[] args) {
        client client = new client();
        client.send("helloQ");
    }

    public void send(String testHello){
        DatagramSocket ds = null;
        try {
            System.out.println("client starts");
            ds = new DatagramSocket();
            byte[] message = testHello.getBytes();
            System.out.println("hostAddress = " + InetAddress.getLocalHost());
            InetAddress host = InetAddress.getByName("localhost");

            int portNumber = 9876;
            DatagramPacket request = new DatagramPacket(message, message.length, host, portNumber);
            ds.send(request);
            System.out.println("Request send, content ==> " + new String(request.getData()));

            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            System.out.println("receive buffer is ready, waiting for reply");

            ds.receive(reply);
            String replyData = new String(reply.getData()).split("\n")[0];
            System.out.println("Reply received, content ==> " + replyData);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ds != null) ds.close();
        }
    }
}
