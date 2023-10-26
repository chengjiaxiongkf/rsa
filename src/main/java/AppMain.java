import cn.hutool.json.JSONUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

public class AppMain {

    private static final String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCm/5/S2pT/jxFz\n" +
            "aHHBG0fFJ7QEAqyE4Zl5O2ulyuUUcHVUVkEIHrQRt1SbPoqeP22yjVBUiD3Rhzyw\n" +
            "kQynUgDzXWeaFeKHMeOzGPcKZRFR0f06K8gogB7Hd9oIG8CP1RZ3eSShq9dsd+21\n" +
            "N2PHR1Xrt5OoiLbyDvBQUzl9MtAtY4MvT9Fnl0EABpsZw2ax8cE2WxCgC3r5y9FK\n" +
            "FtU5HiiJRnehhBDC2KFSofgTIPfb2/Hqly4EBediVnF/nD/zFd21ZbSsyzpDJhWU\n" +
            "eIFuRjTFVVitwKRUqP2mwm5rmiFQEYgKioJpxpownO1E7Aswugu5xvbj4hKQuKLG\n" +
            "50aBACgrAgMBAAECggEAdKbtKmmbWOTEKNyVUXHP8W0pdv+zKOM5xJB4Qoh2+o8P\n" +
            "tkjGBnkECbgJNqQ6Sg4+f1HtAg9lNz6Da0b3Es3aciUV4HFXGl8EEi/nKPCuZ3wK\n" +
            "qh65I2lTlrwmpjFUkmQj3HLX3sLdqRwhBMvbO/GdmBlU4I4XVQCQZ1urNcRP3NOc\n" +
            "ZV+pdX6KtwG6Sv3qk4CCFfCXOLPrR5PE+ur2H1ZY5lgc+TAGTuQAR1g4q2mk5/mX\n" +
            "hQMgbDO0dr60dNe1D0VYGhr09CapWgLsDXaSCYT0WyH+/UXGcuCI+9KnC0qDkskH\n" +
            "3JbHFjh+ln88Q2rN3FjhLDpucK1/hOPWT/Xj7SBSaQKBgQDVf5nkLkzK1ZheiPAy\n" +
            "Ga+PMh1Xse1BeZjguumFUz+gAS1hZpP94MThxAvHlPC6uWeElWRQn7u+qzWHNLbZ\n" +
            "hd8mOjiK++eAvTM5XeR4Apvj8Px6NA8HHzFukHuXMd3UYaTCMz00+fY0/1N7yR24\n" +
            "g5doXPfbNSiFY93LcDI1pdBQjwKBgQDIPkZgb8ZHw+Fo0rLXRCF75vrDCfuNxUzS\n" +
            "g8fZXz4ph4npM3pbjX7B51pFKc7HN2lAV1NegWsK2HNrcEz13/d0/R/7Y06gMWOl\n" +
            "hquSMmQnDl5e+ADmdLatdKO7SD5vRUn4O8EsGjUJflukKqc7lIU5Qfr8SEilbQh/\n" +
            "2JldAOYEpQKBgG4Er1zyrwfnQfmVP5HRwrJb9Nr4CXcwHiPQrf3ShHsDoEsM/T5a\n" +
            "0Zo12IwkqFjQqhOcmHEjnzAGMjNWatN1NSUR44lbflTNKtRpGg7t6T9xM+oVYZkd\n" +
            "7e837Thgrht/pdOda/Eczk9JM9AaN4KZ7TAOmHsRzavPZsArNPSIWezpAoGANaNQ\n" +
            "0LZAI6sZry15UGZknb7ifuomovQtvtoj51s22IMA8yGh7fI5+RDSwmIPjJeQLvb1\n" +
            "UwRRuwb969KaGuaSb5j6Pyl7vzdbnNQ1EyVCZJ/ZsZC3FQRAhrgOv7T7XGzwAnln\n" +
            "Jjwi5tcdDx2JzFDdWtgMthVauE07kJthgWMjOi0CgYBna+5ZhSQO/rYUJ7mrwplG\n" +
            "rrwGlxMZmNYDou4S+WTvHUkIBJ2lB+Lkjl748JIzQLViFP+KaMjfgWOUX9pmWUFP\n" +
            "gqgEIUibWMD/pb3uG+HWQcxsxiWLBLC4gf1/xqZBtNJufeyv7l9Dy6f9Q7ZuI91k\n" +
            "Dyww/gJC3rTlp6NyaE9ptw==";

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApv+f0tqU/48Rc2hxwRtH\n" +
            "xSe0BAKshOGZeTtrpcrlFHB1VFZBCB60EbdUmz6Knj9tso1QVIg90Yc8sJEMp1IA\n" +
            "811nmhXihzHjsxj3CmURUdH9OivIKIAex3faCBvAj9UWd3kkoavXbHfttTdjx0dV\n" +
            "67eTqIi28g7wUFM5fTLQLWODL0/RZ5dBAAabGcNmsfHBNlsQoAt6+cvRShbVOR4o\n" +
            "iUZ3oYQQwtihUqH4EyD329vx6pcuBAXnYlZxf5w/8xXdtWW0rMs6QyYVlHiBbkY0\n" +
            "xVVYrcCkVKj9psJua5ohUBGICoqCacaaMJztROwLMLoLucb24+ISkLiixudGgQAo\n" +
            "KwIDAQAB";

    public static void main(String[] args) throws Exception {
        String data = "{ \"kdp_member_number\": \"123\", \"start_date\": \"20200901\", \"end_date\": \"20241130\", \"page\": 1, \"length\": 10, \"key_version\": \"123\", \"sign\": \"123\" }";
        String dataStr = generateDataString(JSONUtil.parseObj(data).toBean(Map.class));
        String dataStrBase64 = Base64.encode(data.getBytes("UTF-8"));
        System.out.println("待加签字符串:" + dataStr);
        System.out.println("Base64之后:" + dataStrBase64);
        String signature = sign(PRIVATE_KEY, dataStr);
        System.out.println("签名:" + signature);
        System.out.println(valid(dataStrBase64, signature, PUBLIC_KEY) ? "校验通过" : "校验不通过");
    }

    private static String generateDataString(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!entry.getKey().equals("sign")) { // 排除掉sign字段
                sb.append(entry.getKey()).append("=").append(String.valueOf(entry.getValue())).append("&");
            }
        }
        sb.deleteCharAt(sb.length() - 1); // 删除最后一个多余的"&"
        return sb.toString();
    }

    public static String sign(String privateKeyStr, String dataStr) throws Exception {
        // 生成私钥
        byte[] decoded = Base64.decode(privateKeyStr);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(spec);

        // 使用私钥和SHA256withRSA算法进行签名
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(dataStr.getBytes("UTF-8"));

        // 获取签名
        byte[] signature = privateSignature.sign();
        // 将签名转换为Base64编码的字符串
        return Base64.encode(signature);
    }

    public static boolean valid(String signData, String sign, String publicKeyStr) throws Exception {
        // 生成公钥
        byte[] decoded = Base64.decode(publicKeyStr);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(spec);

        // 使用公钥和SHA256withRSA算法进行解签
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(signData.getBytes("UTF-8"));
        return publicSignature.verify(Base64.decode(sign));
//        // 从classpath中获取PEM公钥文件的输入流
//        ClassPathResource classPathResource = new ClassPathResource("classpath:rsa_public_key.pem");
//        // 使用HuTool的IoUtil将输入流转换为字符串
//        String publicKeyPem = IoUtil.read(new InputStreamReader(classPathResource.getStream(), StandardCharsets.UTF_8));
//        // 使用HuTool的SecureUtil生成RSA对象
//        RSA rsa = SecureUtil.rsa(null, publicKeyPem);
//        PublicKey publicKey = rsa.getPublicKey();
//        Signature publicSignature  = Signature.getInstance("SHA256withRSA");
//        publicSignature.initVerify(publicKey);
//        publicSignature.update(signData.getBytes("UTF-8"));
//        return publicSignature.verify(Base64.decode(sign));
    }
}
