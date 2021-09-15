package WebClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpClientImpl implements HttpClient{
    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {

        HttpClientImpl httpClient = new HttpClientImpl();
        url=httpClient.getUrl(url,params);
        System.out.println(url);
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection = httpClient.setRequests(connection, headers);
            connection.setRequestMethod("GET");
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> params) {
        HttpClientImpl httpClient = new HttpClientImpl();
        url=httpClient.getUrl(url,params);
        try {
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection = httpClient.setRequests(connection, headers);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            String jsonInputString = "{\"name\":\"Tenali Ramakrishna\", \"gender\":\"male\", \"email\":\"tenali.ramakrishna1@gmail.com\", \"status\":\"active\"}";

            try (OutputStream outputStream = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                outputStream.write(input, 0, input.length);
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            )) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection setRequests(HttpURLConnection connection, Map<String, String> headers) {
        for (String header : headers.keySet()) {
            connection.setRequestProperty(header, headers.get(header));
        }
        return connection;
    }
    private String getUrl(String url, Map<String, String> params){
        url+="?";
        for (String param: params.keySet()){
            url+=param+"="+params.get(param)+"&";
        }
        return url;
    }
}
