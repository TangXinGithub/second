import jdk.dynalink.beans.StaticClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class http {
    static HttpClient httpClient = HttpClient.newBuilder().build();

    void http() throws IOException {
        URL url = new URL("https://baidu.com");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//       设置请求方式
        conn.setRequestMethod("GET");
//        设置缓存
        conn.setUseCaches(false);
        conn.setConnectTimeout(5000); // 请求超时5秒
// 设置HTTP头:
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 11; Windows NT 5.1)");
// 连接并发送HTTP请求:
        conn.connect();
// 判断HTTP响应是否200:
        if (conn.getResponseCode() != 200) {
            System.out.println(conn.getResponseCode());
            throw new RuntimeException("bad response");
        }
// 获取所有响应Header:
        Map<String, List<String>> map = conn.getHeaderFields();
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
// 获取响应内容:
        InputStream input = conn.getInputStream();
        System.out.println("输出" + input);
    }

    //jdk11
    void httpjdk11() throws URISyntaxException, IOException, InterruptedException {
        String url = "https://www.sina.com.cn/";
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                // 设置Header:
                .header("User-Agent", "Java HttpClient").header("Accept", "*/*")
                // 设置超时:
                .timeout(Duration.ofSeconds(5))
                // 设置版本:
                .version(HttpClient.Version.HTTP_2).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        // HTTP允许重复的Header，因此一个Header可对应多个Value:
        Map<String, List<String>> headers = response.headers().map();
        for (String header : headers.keySet()) {
            System.out.println(header + ": " + headers.get(header).get(0));
        }
        System.out.println(response.body().substring(0, 1024) + "...");
    }
}
