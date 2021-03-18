import com.google.gson.Gson;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
//    https://www.cnblogs.com/lelexiong/p/9282410.html
//    https://www.cnblogs.com/54tester/p/11516004.html
// https://www.xttblog.com/?p=4902

//    https://www.cnblogs.com/longmaodaxia/archive/2019/07/19/11212833.html
//    https://mvnrepository.com/artifact/commons-codec/commons-codec/1.11

    @Test
    public void case1() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void case2() {
        Assert.assertEquals("haha", "haha");

    }

    @DataProvider
    public Object[][] datas() {
        Object[][] datas = {
                {"13758209593", "209593", 1},
                {"15167186163", "123", -5},
                {"15167186163", "123rewrewrewrwqwer", -5},
                {"15163", "123rewrewrewrwqwer", -5},
        };
        return datas;
    }

    @Test(dataProvider = "datas")
    public void case3(String phone, String pwd, int expCode) {
        String url = "http://115.236.5.90:8082/api/Transport/EventHandle";
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        String jsonString = "{\"AppKey\":\"HTXFV-FH8YX-VCY69-JJGBK-7R6XP\"," +
                "\"Ver\":\"3.9.0\",\"Bver\":\"20210127\",\"Cmd\":\"Login\"," +
                "\"JsonParts\":{\"school\":\"\"," +
                "\"username\":\""+phone+"\",\"pass\":\""+pwd+"\"," +
                "\"cmd\":\"user\",\"user\":\"2020110413203003\"},\"SKey\":" +
                "\"01d1561eedbdea5461edb1b11c3aae2d\",\"SessionId\":" +
                "\"836AD59F-DDE1-4003-840D-272E7EBBB7DF\"," +
                "\"Noce\":\"1\",\"AppId\":1,\"UnixTime\":1615991611}";

        BasicNameValuePair basicNameValuePair1 = new BasicNameValuePair("json", jsonString);
        params.add(basicNameValuePair1);

//        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("pwd", pwd);
//        params.add(basicNameValuePair2);

//        {"AppKey":"HTXFV-FH8YX-VCY69-JJGBK-7R6XP","Ver":"3.9.0","Bver":"20210127","Cmd":"Login","JsonParts":{"school":"","username":"13758209593","pass":"209593","cmd":"user","user":"2020110413203003"},"SKey":"01d1561eedbdea5461edb1b11c3aae2d","SessionId":"836AD59F-DDE1-4003-840D-272E7EBBB7DF","Noce":"1","AppId":1,"UnixTime":1615991611}

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            System.out.println("method : " + httpPost.getMethod());
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            String resutl = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("返回结果 : " + resutl);

            Gson gson = new Gson();
            ResultData data = gson.fromJson(resutl, ResultData.class);
            int resultCode = data.getResultCode();
            System.out.println("ResultCode : " + resultCode);
            Assert.assertEquals(expCode, resultCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
