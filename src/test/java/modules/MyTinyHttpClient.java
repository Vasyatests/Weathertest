package modules;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Fed on 18.10.2019.
 */
public class MyTinyHttpClient {


        public static String getResponseFromHttpUrl(URL url, String cookie) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setRequestProperty("Cookie", cookie);
        urlConnection.setRequestProperty("Accept-Encoding", "identity");
        urlConnection.setRequestProperty("Accept-Charset", "UTF-8");
        urlConnection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 YaBrowser/19.9.3.314 Yowser/2.5 Safari/537.36");
        //urlConnection.setDoInput(true);
        //urlConnection.setUseCaches(false);
        Map<String, List<String>> sbs = urlConnection.getRequestProperties();
        urlConnection.connect();

        //urlConnection.getContent();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in, "UTF-8");
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                 return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static String sendPost(URL url, String params, String cookie) throws Exception {
        HttpsURLConnection httpClient = (HttpsURLConnection) url.openConnection();
        httpClient.setRequestMethod("POST");
        //httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //httpClient.setRequestProperty("Accept-Encoding", "identity");
        httpClient.setRequestProperty("Accept-Charset", "UTF-8");
        httpClient.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 YaBrowser/19.9.3.314 Yowser/2.5 Safari/537.36");
        httpClient.addRequestProperty("Cookie", cookie);
        Map<String, List<String>> sbs = httpClient.getRequestProperties();
        httpClient.setDoOutput(true);
        // Send post request
        //httpClient.connect();
        //httpClient.getContent();


        try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
            wr.writeBytes(params);
            wr.flush();
        }
        int responseCode = httpClient.getResponseCode();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(httpClient.getInputStream(), "UTF-8"))) {
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        return response.toString();
        }
    }


}
