import org.apache.commons.collections.CollectionUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by caiyusong on 2017/8/30.
 */
public class HttpClientUtil {

    CloseableHttpClient httpclient = HttpClients.createDefault();

    public String get(String url , List<NameValuePair> params) throws IOException {
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = httpclient.execute(httpGet);

        String content = EntityUtils.toString(response.getEntity());

        return content;
    }

    public String post(String url , List<NameValuePair> params) throws IOException {
        return post(url,params,null);
    }

    public String post(String url , List<NameValuePair> params,List<NameValuePair> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        if (!CollectionUtils.isEmpty(headers)){
            for (NameValuePair header : headers) {
                httpPost.addHeader(header.getName(),header.getValue());
            }
        }

        CloseableHttpResponse response  = httpclient.execute(httpPost);
        try {
            String content = EntityUtils.toString(response.getEntity());
            return content;
        } finally {
            response.close();
        }
    }

}
