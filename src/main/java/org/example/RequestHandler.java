package org.example;

public class RequestHandler {
    private static RequestHandler INSTANCE;

    private RequestHandler() {}

    public static RequestHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestHandler();
        }
        return INSTANCE;
    }

   // String[] cmdList = {"more_expensive", "all", "all_sorted", "help"};

    public String getAction(String inRequest) {
        String responseRequest = "";

        switch (inRequest) {
            case "more_expensive":
                responseRequest = Garage.getInstance().getExpensive();
                break;

            case "all":
                responseRequest = Garage.getInstance().getAll();
                break;

            case "all_sorted":
                responseRequest = Garage.getInstance().getSorted();
                break;

            default:
                responseRequest = "<p>Command not found!</p>";
                break;
        }

        return responseRequest;
    }
}
