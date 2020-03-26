import java.io.IOException;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) throws IOException {
        commWithServer();
    }

    private static void commWithServer() throws IOException {
        try(
        Socket s = new Socket("localhost", MyServer.MY_SERVER_PORT);
        ){
            System.out.println("客户端连接到："+s.getRemoteSocketAddress());
            MyChat myChat = new MyChat("服务器端", null, s);
            myChat.chatting();
        }
    }
}
