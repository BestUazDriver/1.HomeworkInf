package WebClient;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        HttpClientImpl httpClient = new HttpClientImpl();
        Map<String, String> headers = new HashMap<>();
        Map<String, String> params = new HashMap<>();
        headers.put("Content-Type", "application/json");
        params.put("page", "1");
        System.out.println(httpClient.get("https://postman-echo.com/get", headers, params));
        System.out.println(httpClient.post("https://postman-echo.com/post", headers, params));
    }
}
