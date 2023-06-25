package org.example;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;

public class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        URI uri = exchange.getRequestURI();
        System.out.println(uri);

        String method = exchange.getRequestMethod();
        System.out.println(method);

        String s = read(is); // .. read the request body
        System.out.println(s);

        String responseRequest = response(uri.toString());
        String response = "<!doctype html>\n" +
                "<html lang=en>\n" +
                "<head>\n" +
                "<meta charset=utf-8>\n" +
                "<title>My GaraGe</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "</br>Query  Requested :" +
                "<table>\n" +
                "<tr style=\"background-color: lightgray;\">" +
                "<th style=\"color: blue; font-weight: bold;\">Id</th>" +
                "<th style=\"color: red; font-weight: bold;\">Brand</th>" +
                "<th style=\"color: green; font-weight: bold;\">Price</th>" +
                "<th style=\"color: orange; font-weight: bold;\">Quantity</th>" +
                "</tr>"+
                responseRequest +
                "</table>\n" +
                "</body>\n" +
                "</html>\n";

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    private String read(InputStream is) {
        BufferedReader br = new BufferedReader( new InputStreamReader(is) );
        System.out.println("\n");
        String received = "";
        while (true) {
            String s = "";
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            received += s;
        }
        return received;
    }

    public String response(String uri) {
        String[] requestAns = uri.split("[/?" +
                "" +
                "&]");
        ArrayList<String> comandi = new ArrayList<>();
        if (requestAns.length == 0) {
            return "No data";
        }

        for (String req : requestAns) {
            if (req.contains("Get")) {
                comandi.add(req);
            }
        }

        String requestAnswer = "";
        String[] s;
        if (comandi.get(0).contains("Get")) {
            s = comandi.get(0).split("=");
            requestAnswer = RequestHandler.getInstance().getAction(s[1]);
        }

        return requestAnswer;
    }
}
