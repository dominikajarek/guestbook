import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GuestBookApp {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                try (Socket client = serverSocket.accept()) {
                    HttpGet.handleClient(client);
                }
            }
        }
    }
}
