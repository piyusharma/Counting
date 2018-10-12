import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


class HTTPRequests {

    static String getToken(String accessKey, String secretKey, String username, String password) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("https://www.reddit.com/api/v1/access_token");
        String userCredentials = accessKey + ":" + secretKey;
        String basicAuth = "Basic " + new String(new Base64().encode(userCredentials.getBytes()));
        httpPost.addHeader("Authorization", basicAuth);
        httpPost.addHeader("User-Agent", "Something");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("grant_type", "password"));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        return bufferedReader.readLine();
    }

    static String getRequest(String url, List<NameValuePair> header) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        for (NameValuePair nameValuePair : header) {
            httpGet.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
        HttpResponse httpResponse = httpClient.execute(httpGet);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        return bufferedReader.readLine();
    }

    static String postRequest(String url, List<NameValuePair> header, List<NameValuePair> postData) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        for (NameValuePair nameValuePair : header) {
            httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
        }
        httpPost.setEntity(new UrlEncodedFormEntity(postData, "UTF-8"));
        HttpResponse httpResponse = httpClient.execute(httpPost);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
        return bufferedReader.readLine();
    }
}
