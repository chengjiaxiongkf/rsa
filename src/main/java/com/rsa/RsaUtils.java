package com.rsa;

import cn.hutool.json.JSONUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

public class RsaUtils {
    private String rsaPrivateKey;
    private String rsaPublicKey;

    public RsaUtils(String rsaPrivateKey,String rsaPublicKey) {
        this.rsaPublicKey = rsaPublicKey;
        this.rsaPrivateKey = rsaPrivateKey;
    }

    public RsaUtils() {
        this.rsaPublicKey = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApv+f0tqU/48Rc2hxwRtH\n" +
                "xSe0BAKshOGZeTtrpcrlFHB1VFZBCB60EbdUmz6Knj9tso1QVIg90Yc8sJEMp1IA\n" +
                "811nmhXihzHjsxj3CmURUdH9OivIKIAex3faCBvAj9UWd3kkoavXbHfttTdjx0dV\n" +
                "67eTqIi28g7wUFM5fTLQLWODL0/RZ5dBAAabGcNmsfHBNlsQoAt6+cvRShbVOR4o\n" +
                "iUZ3oYQQwtihUqH4EyD329vx6pcuBAXnYlZxf5w/8xXdtWW0rMs6QyYVlHiBbkY0\n" +
                "xVVYrcCkVKj9psJua5ohUBGICoqCacaaMJztROwLMLoLucb24+ISkLiixudGgQAo\n" +
                "KwIDAQAB\n" +
                "-----END PUBLIC KEY-----\n";
        this.rsaPrivateKey = "-----BEGIN PRIVATE KEY-----\n" +
                "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCm/5/S2pT/jxFz\n" +
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
                "Dyww/gJC3rTlp6NyaE9ptw==\n" +
                "-----END PRIVATE KEY-----\n";
    }

    public String generateDataString(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!entry.getKey().equals("sign")) { // 排除掉sign字段
                sb.append(entry.getKey()).append("=").append(String.valueOf(entry.getValue())).append("&");
            }
        }
        sb.deleteCharAt(sb.length() - 1); // 删除最后一个多余的"&"
        return sb.toString();
    }

    private PublicKey getPublicKeyFromString(String rsaPublicKey) throws Exception {
        String publicKeyString = rsaPublicKey.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] publicKeyBytes = Base64.decode(publicKeyString);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePublic(keySpec);
    }

    private PrivateKey getPrivateKeyFromString(String rsaPrivateKey) throws Exception {
        String privateKeyString = rsaPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] privateKeyBytes = Base64.decode(privateKeyString);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(keySpec);
    }

    private byte[] sign(String data) throws Exception {
        // 将PEM格式的公钥和私钥转换为Java对象
        PrivateKey privateKey = this.getPrivateKeyFromString(rsaPrivateKey);
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes("UTF-8"));
        return signature.sign();
    }

    /**
     * 加签
     * @param data
     * @return
     * @throws Exception
     */
    public String signStr(String data) throws Exception {
        // 要签名的数据
        String dataStr = this.generateDataString(JSONUtil.parseObj(data).toBean(Map.class));
        String dataStrBase64 = Base64.encode(dataStr.getBytes("UTF-8"));
        // 使用私钥进行签名
        byte[] signature = this.sign(dataStrBase64);
        return Base64.encode(signature);
    }

    private boolean verify(String data, byte[] signature) throws Exception {
        PublicKey publicKey = this.getPublicKeyFromString(rsaPublicKey);
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(data.getBytes("UTF-8"));
        return verifier.verify(signature);
    }

    public boolean verifyStr(String data, String sign) throws Exception {
        String dataStr = this.generateDataString(JSONUtil.parseObj(data).toBean(Map.class));
        String dataStrBase64 = Base64.encode(dataStr.getBytes("UTF-8"));
        return this.verify(dataStrBase64,Base64.decode(sign));
    }
}
