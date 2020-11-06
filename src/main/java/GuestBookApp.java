import com.sun.net.httpserver.HttpServer;
import controllers.EntryController;
import models.Entry;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GuestBookApp {

    public static void main(String[] args) throws IOException {

        List<Entry> entryList = new ArrayList<>();
        Entry entry1 = new Entry("John", "Test", "john@gmail.com", LocalDate.now());
        Entry entry2 = new Entry("Jane", "Test", "jane@gmail.com", LocalDate.now());

        entryList.add(entry1);
        entryList.add(entry2);

        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8008), 0);
        httpServer.createContext("/guestbook", new EntryController(entryList));
        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("server has started on port " + httpServer.getAddress().getPort());
    }
}
