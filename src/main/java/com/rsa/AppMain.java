package com.rsa;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

public class AppMain {
    /**
     * BEGIN RSA PRIVATE KEY私钥格式转换成BEGIN PRIVATE KEY
     * linux系统【openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in 2.pem -out 2_pkcs8.pem】
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println("args: " + JSONUtil.toJsonStr(args));
        // 私钥
//        String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
//                "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC00wlnhtRyF9AJ\n" +
//                "uT2y9RrSv0zfZe21J6F24cgKYQzkdpQK3ZxjwDG1vPgO9HoHEUPdeKrkfAw+tpij\n" +
//                "FKW5KOw4AmDHM8dhcmtzSl5WYahNIY6mCRD9w3Ov4tthZXeoIJdG4ZYg5bg2+nBq\n" +
//                "8BbtqlCwLUtFZyhxEX52nJZQcx97TVLWmlnI0obdzxwNsrjqljceT/9tDfwk4Yfh\n" +
//                "Ed79yx/URpGXrPFjfFYW/Jd9NEVcIdMY1bFfrmKEEH4Znby7UQDuK+AiGZfLKZqT\n" +
//                "YlneCkpMjHjCjTnVxMnYtxHQAQhNw5tsSjD5VtOOW75+ft95uUbrgnfvcgZ/Plfg\n" +
//                "o62XWQDTAgMBAAECggEAYJjEU1vBrdlut2MPxMQPOjoblNPcsoOjS+C9fl3uBzYZ\n" +
//                "S9yrKNaJN67lTQfpEFF20Z8em8RwvifJToU/KrTjy9/pV0Ef57y6DfLSho7Q27Nr\n" +
//                "guMhAo8hA/rKezM7QsN2TNVB3/0X9ba/y0DK3te3FwZ7SW4cOpq/1hk8a2m0jacQ\n" +
//                "vsal5IWaU/vKLk77ILFUlxBf3J2Q/322lXH5gvZvMf7MNpgoCiNGs7g68OQu0Prz\n" +
//                "dG3NMUQXJkhkLc4KAgq67J8nU2ZyUzmgez7V5j4+y9e6prQe37ymXiFQLVd0PRmB\n" +
//                "Ld/raNMmg6SvA5LkVhHKpDz6s+nw3R+CjbpPbeoqwQKBgQDjCgRVyeIVZz0TM5eZ\n" +
//                "KeKuZBVx8McPV0XjNmQLWExQQwssIM93Ss6OrsgcDSGym9j05KSD7q4E5MVDURf3\n" +
//                "ec1iRd/94opGzqjV35J2zkS3HcOWVBOIBYxB0EbZvN3PxaPp3fabGFvAIx1WdxnS\n" +
//                "NU8ywxFxANpUxUWdQXWvEhaGsQKBgQDL499aH/559oS15tXWeOgkrdl0Xj4jed0O\n" +
//                "vlv/C3i9k3+0knVrdIol1Fh3zX/kiW4kcwnesiTKwuhZyRsdv13Dx2MmzaquO97v\n" +
//                "3gIoQaASBPv+PnU1PmtKuMgYQwy/BzntfcN/HipGCvKw6jwAdsAnGKog4j6vO4a/\n" +
//                "s7h6kCDowwKBgE3ApHNszAxgar4ksQo3GfZ3OBDpUIKkdL63ZdOszZW/IBhgtCw6\n" +
//                "ZjrfCJDyvlOYo9haZSz5lIcAgb3oC7ko8XlyTJ958bkGFTQJePNO+KFthAn7iSTQ\n" +
//                "IZR4a4st7xi0qsnwxf+fe+x1Ghr+ds1SjGjp/RMiI6NcW9bbEB6Ap05BAoGBAKx4\n" +
//                "sGE+mzd0b0KoZ88rK8SLQ12KsymzVmm4YX3XqGVFNkv7cFRbmtjxTXzaWwtprcAr\n" +
//                "h/fX3gOe6Im68rMxRBHuwlL6uxz0DFwAAvcmvE9B5CBnmSJXEM9+i9TqMwgNBtV9\n" +
//                "AziuvbfOXwmEuwjxaw4ncRKsD7aPtibH0o/rB8udAoGAO8aAeuaMY270MupvdEcr\n" +
//                "MwESa72xiU2dQB2cGGUIgIqhsD10ZDtoBvmn8qmMAehRXU8LMIbhnb85v9+CJyP2\n" +
//                "VwLPZPbuLKQxbInzBFnjpvH5bD2g1g3dJrjVyIj+/26yALSJJBvrcP2j69NCjS+8\n" +
//                "Dk7AvdoH/YyVU/uFA89zbTg=\n" +
//                "-----END PRIVATE KEY-----";
        // 另外一个私钥
        String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
                "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAMrKysecLTlvSx0x\n" +
                "Z7sFb3xGd6v3LymAZ7AHDIw8YZaEm8r7Fnqj60/z/eaGcdDB3d7+nmfTzuD5qkLT\n" +
                "1o+xInc8Hp2pzNc8REbPXw6Guyt9D+6K89Lryr/uajfHLhrIle2COlvIx8e4YaMB\n" +
                "2EyAdnlw/iVt4d2yv/hrY3rsRuCfAgMBAAECgYByckRCprc3JMIldY5vE8A5SKOd\n" +
                "H2E0cM0ToMbKFgGVKr2prk6fZAcXFkICaXmuMNsAUH4hZn3opb4a/OaoLXWfBuFZ\n" +
                "SY+rNN3elx1OU1Xz2YRwb4JmAwTFhi7+WN7RrIUav7rB+F9wyry0lXlznx/cHfDZ\n" +
                "Z8CPchcCur5PxRwbYQJBAPhqt92Ac/ehAIVx7KYoYYQpKFGMF17Wulz12r4GIw8y\n" +
                "p2cYrExKAof0bJ76Q8TFVT2y/XzubBpkmHy2II9s6OkCQQDQ+4jOzCBY08/o6ix9\n" +
                "jUyEIhQQcBHIfKKjklDwF5LeO5ApIV6CpEYNmdVYUZMeUEliXgA/u20lot5ZWD4A\n" +
                "kwhHAkEAiC5Ao13itPJ/DaiZebETXFcPsjyhJy2BtqpUgu1sVM2I9byR37SG1K8K\n" +
                "yNAbTIh9JTi1O8KcYo+ZBGz3RokG2QJBAIHPzRJPv8QNhjcyxwISNDZbiMURKCq2\n" +
                "KLycVYRd0FAVBZ5W6QMTkaQ/K0PaIB2TV5Ivu83a2ZioS5+J7bGjo18CQQCh7EPT\n" +
                "lHdX4lljza+VBcVBJAXX4AfdPB8FGM0nHvdhiSwWWZgaPg746XAduzH48Qla2MuU\n" +
                "j3DB5LrEaQ3L5D1X\n" +
                "-----END PRIVATE KEY-----\n";
        // 接口参数
        String data = "{ \"partner_code\": \"KDPCTF\", \"kdp_member_number\": \"00002472166\", \"partner_serial_number\": \"PointAsCash-20231027161327-1698394407812\", \"partner_order_number\": \"ORDER-20231027161327-1698394407812\", \"partner_member_number\": \"123456789\", \"partner_member_tier_code\": \"金卡\", \"deduct_type\": \"3\", \"deduct_value\": \"\", \"point_delta\": \"-1000\", \"transaction_at\": \"20231027161327\", \"transaction_type\": 1, \"key_version\": \"v3\", \"sign\": \"JeCfGhGef5jX3XqmBMWLeta1lVqXKiSAuS8YFR0DWwNSCx4qjDyfYOC4Z9xKRdcvAOxWU+cv8emiY35FIy0SaVe2ppoyn5hJ9gF8F2CsF9Trb0t5LNMCkhFcNihEAphbDzSLc9qXIef7zciPta3qVa+0Jf0jDAkNCQkhgw92M1Y=\", \"transaction_desc\": { \"partner_campaign_code\": \"201704001\", \"partner_store_code\": \"STORE-001\", \"partner_store_name\": { \"en\": \"jichang\", \"zh-cn\": \"jichang\", \"ZH-HK\": \"jichang\" }, \"currency\": \"2\", \"amount\": \"2500.00\", \"payment\": [ { \"method\": \"UnionPay\", \"amount\": \"1500.00\" }, { \"method\": \"Coupon\", \"amount\": \"1000.00\" } ], \"skus\": [ { \"sku_code\": \"SKU-1234\", \"sku_name\": \"钛合金宝石\", \"sku_category\": \"SKU-CAT-A\", \"sku_brand\": \"Disney\", \"sku_tag\": \"5A1\", \"quantity\": 1, \"list_unit_price\": \"128.00\", \"actual_unit_price\": \"100.00\", \"additional_info\": { \"sku_subbrand\": \"abc\" } }, { \"sku_code\": \"SKU-1235\", \"sku_name\": \"动感扭片银925手链\", \"sku_category\": \"SKU-CAT-B\", \"sku_brand\": \"Disney\", \"sku_tag\": \"5A1\", \"quantity\": 1, \"list_unit_price\": \"1280.00\", \"actual_unit_price\": \"1000.00\", \"additional_info\": { \"sku_subbrand\": \"abc\" } } ], \"description\": \"积分抵现扣分\" } }";
        RsaUtils rsaUtils = new RsaUtils(privateKey, "");
        // 使用私钥进行加签
        String sign = rsaUtils.signStr(args.length > 0 && !StrUtil.isEmpty(args[0]) ? args[0] : data);
        System.out.println("sign: " + sign);
        // 使用公钥进行解签
//        boolean isValid = rsaUtils.verifyStr(data, sign);
//        System.out.println("Is valid: " + isValid);
    }
}
