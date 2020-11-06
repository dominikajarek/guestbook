package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import helpers.Parser;
import models.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class EntryController implements HttpHandler {
    private List<Entry> entryList;

    public EntryController(List<Entry> entryList) throws IOException {
        this.entryList = entryList;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        String response = "";
        try {
            switch (method) {
                case "GET":
                    response = get(exchange);
                    System.out.println(response);
                    ResponseController.sendResponse(exchange, response, 200);
                    break;
                case "POST":
                    response = post(exchange);
                    System.out.println(response);
                    ResponseController.sendResponse(exchange, response, 201);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            ResponseController.sendResponse(exchange, e.getMessage(), 404);
        }
    }

    private String post(HttpExchange exchange) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody(), "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        Map<String, String> body = Parser.parseFormData(bufferedReader.readLine());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(body.get("date"), formatter);

        Entry entry = new Entry(body.get("name"), body.get("comment"), body.get("email"), localDate);
        this.entryList.add(entry);

        return entriesToJson();
    }

    private String get(HttpExchange exchange) throws JsonProcessingException {
        return entriesToJson();
    }

    private String entriesToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this.entryList);
    }
}

