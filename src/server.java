import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class server{
    public static void main(String[] args) {
        server server = new server();
        server.serverStart(9876);
    }

    public void serverStart(int portNumber) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(portNumber);
            byte[] buffer = new byte[1000];
            System.out.println("server started, socket and buffer are ready");

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);
                String realData = new String(request.getData()).split("\n")[0];
                System.out.println("request received, content ==> " + realData);

                DatagramPacket reply= new DatagramPacket((realData + "\n").getBytes(), (realData + "\n").length(),
                        request.getAddress(), request.getPort());
                socket.send(reply);
                System.out.println("reply send, content ==> " + new String(reply.getData()));
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
        }

    }
}
