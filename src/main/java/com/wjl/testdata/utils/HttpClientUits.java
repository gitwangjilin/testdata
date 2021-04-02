package com.wjl.testdata.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUits {
    private static Log logger = LogFactory.getLog(HttpClientUits.class);


    /**
     * HttpClient连接SSL
     */
    public void ssl() {
        CloseableHttpClient httpclient = null;
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            FileInputStream instream = new FileInputStream(new File("d:\\tomcat.keystore"));
            try {
                // 加载keyStore d:\\tomcat.keystore
                trustStore.load(instream, "123456".toCharArray());
            } catch (CertificateException e) {
                e.printStackTrace();
            } finally {
                try {
                    instream.close();
                } catch (Exception ignore) {
                }
            }
            // 相信自己的CA和所有自签名的证书
            SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
            // 只允许使用TLSv1协议
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf)
                    .build();
            // 创建http请求(get方式)
            HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");
//			System.out.println("executing request" + httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
//				System.out.println(response.getStatusLine());
                if (entity != null) {
//					System.out.println("Response content length: " + entity.getContentLength());
//					System.out.println(EntityUtils.toString(entity));
                    EntityUtils.consume(entity);
                }
            } finally {
                response.close();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * post方式提交表单（模拟用户登录请求）
     */
    public static String postForm(String uri, Map<String, String> map, String charset) {
        String result = "";
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
//		HttpPost httppost = new HttpPost("http://localhost:8080/ssm/http.action");
        HttpPost httppost = new HttpPost(uri);
        // 创建参数队列
//		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//		formparams.add(new BasicNameValuePair("username", "高阳"));
//		formparams.add(new BasicNameValuePair("password", "123456"));

        //设置参数
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            formparams.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }


        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, charset);
            httppost.setEntity(uefEntity);
//			System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, charset);
//					System.out.println("Response content: "+ result);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return charset;
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
     */
    public static String post(String uri, Map<String, String> map, String charset) {
        String result = "";
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(uri);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            formparams.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }

        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formparams, charset);
            httppost.setEntity(uefEntity);
//			String s = "{'message_header':{'buss_id':'210'},'message_content':{"
//					+ "'phoneNum':'17316561539'}}";
//			StringEntity se = new StringEntity(s);
//			httppost.setEntity(se);
//			System.out.println("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, charset);
//					System.out.println("Response content: "+ result);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 无参数名调用
     * 发送 post请求访问
     */
    public static String post(String uri, String parms, String charset) {
        logger.info("请求地址： " + uri);
        logger.info("请求参数： " + parms);
        String result = "";
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = createSSLClientDefault();
        // 创建httppost
        HttpPost httppost = new HttpPost(uri);
        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.29 Safari/525.13";
        httppost.setHeader(HttpHeaders.USER_AGENT, userAgent);
        StringEntity uefEntity;
        try {
            //设置服务超时    2018-5-17
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(5000).setConnectionRequestTimeout(1000)
                    .setSocketTimeout(60000).build();

            httppost.setConfig(requestConfig);
            uefEntity = new StringEntity(parms, "");
            httppost.setEntity(uefEntity);
            logger.info("executing request " + httppost.getURI());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {    //新增
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        result = EntityUtils.toString(entity, charset);
                        logger.info("请求地址：" + uri + ",  响应结果： " + result);
                    }
                    entity.getContent().close();   //新增
                }
            } finally {
                response.close();
                httppost.releaseConnection();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 发送 get请求
     */
    public static String get(String uri, String parms, String charset) {
        String result = "";
        try {
            parms = URLEncoder.encode(parms, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            HttpGet httpget = new HttpGet(uri + "?parm=" + parms);
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // 打印响应内容
                    result = EntityUtils.toString(entity, charset);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 发送 get请求
     */
    public static String get(String uri, Map<String, String> map, String charset) {
        String result = "";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {

            List<NameValuePair> params = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                params.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
////			URI uri = URIUtils.createURI("http", "www.google.com", -1, "/search",
//			URLEncodedUtils.format(params, "UTF-8"), null);
//
            String parms = "?" + URLEncodedUtils.format(params, charset);
//			System.out.println("parms===="+parms);

            // 创建httpget.
//			HttpGet httpget = new HttpGet("http://192.168.0.143:8080/ssm/http2.action"+parms+"&nn=普华");
            HttpGet httpget = new HttpGet(uri + parms);
//			System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
//				System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
//					System.out.println("Response content length: "+ entity.getContentLength());
                    // 打印响应内容
                    result = EntityUtils.toString(entity, charset);
                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static CloseableHttpClient createSSLClientDefault() {

        try {
            //SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
            // 在JSSE中，证书信任管理器类就是实现了接口X509TrustManager的类。
            //我们可以自己实现该接口，让它信任我们指定的证书。
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            //信任所有
            X509TrustManager x509mgr = new X509TrustManager() {
                //该方法检查客户端的证书，若不信任该证书则抛出异常
                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String string) {
                }

                //该方法检查服务端的证书，若不信任该证书则抛出异常
                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String string) {
                }

                //返回受信任的X509证书数组。
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
//                sslContext.init(null, new TrustManager[] { x509mgr }, null);
            sslContext.init(null, new TrustManager[]{x509mgr}, new java.security.SecureRandom());

            ////创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            //  HttpsURLConnection对象就可以正常连接HTTPS了，无论其证书是否经权威机构的验证，只要实现了接口X509TrustManager的类MyX509TrustManager信任该证书。
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();

        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 创建默认的httpClient实例.
        return HttpClients.createDefault();

    }

}