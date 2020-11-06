package controllers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;

public class ResponseController {

    protected static void sendResponse(HttpExchange exchange, String response, int status) throws IOException {
        if (status == 200) {
            exchange.getResponseHeaders().put("Content-type", Collections.singletonList("application/json"));
            exchange.getResponseHeaders().put("Access-Control-Allow-Origin", Collections.singletonList("*"));
        }
        exchange.sendResponseHeaders(status, response.getBytes().length);

        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
