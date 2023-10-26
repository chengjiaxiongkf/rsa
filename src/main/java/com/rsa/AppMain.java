package com.rsa;

import cn.hutool.json.JSONUtil;
import com.rsa.RsaUtils;

public class AppMain {
    public static void main(String[] args) throws Exception {
        System.out.println("args: "+ JSONUtil.toJsonStr(args));
        // 私钥
        String privateKey = "-----BEGIN PRIVATE KEY----- MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC00wlnhtRyF9AJ uT2y9RrSv0zfZe21J6F24cgKYQzkdpQK3ZxjwDG1vPgO9HoHEUPdeKrkfAw+tpij FKW5KOw4AmDHM8dhcmtzSl5WYahNIY6mCRD9w3Ov4tthZXeoIJdG4ZYg5bg2+nBq 8BbtqlCwLUtFZyhxEX52nJZQcx97TVLWmlnI0obdzxwNsrjqljceT/9tDfwk4Yfh Ed79yx/URpGXrPFjfFYW/Jd9NEVcIdMY1bFfrmKEEH4Znby7UQDuK+AiGZfLKZqT YlneCkpMjHjCjTnVxMnYtxHQAQhNw5tsSjD5VtOOW75+ft95uUbrgnfvcgZ/Plfg o62XWQDTAgMBAAECggEAYJjEU1vBrdlut2MPxMQPOjoblNPcsoOjS+C9fl3uBzYZ S9yrKNaJN67lTQfpEFF20Z8em8RwvifJToU/KrTjy9/pV0Ef57y6DfLSho7Q27Nr guMhAo8hA/rKezM7QsN2TNVB3/0X9ba/y0DK3te3FwZ7SW4cOpq/1hk8a2m0jacQ vsal5IWaU/vKLk77ILFUlxBf3J2Q/322lXH5gvZvMf7MNpgoCiNGs7g68OQu0Prz dG3NMUQXJkhkLc4KAgq67J8nU2ZyUzmgez7V5j4+y9e6prQe37ymXiFQLVd0PRmB Ld/raNMmg6SvA5LkVhHKpDz6s+nw3R+CjbpPbeoqwQKBgQDjCgRVyeIVZz0TM5eZ KeKuZBVx8McPV0XjNmQLWExQQwssIM93Ss6OrsgcDSGym9j05KSD7q4E5MVDURf3 ec1iRd/94opGzqjV35J2zkS3HcOWVBOIBYxB0EbZvN3PxaPp3fabGFvAIx1WdxnS NU8ywxFxANpUxUWdQXWvEhaGsQKBgQDL499aH/559oS15tXWeOgkrdl0Xj4jed0O vlv/C3i9k3+0knVrdIol1Fh3zX/kiW4kcwnesiTKwuhZyRsdv13Dx2MmzaquO97v 3gIoQaASBPv+PnU1PmtKuMgYQwy/BzntfcN/HipGCvKw6jwAdsAnGKog4j6vO4a/ s7h6kCDowwKBgE3ApHNszAxgar4ksQo3GfZ3OBDpUIKkdL63ZdOszZW/IBhgtCw6 ZjrfCJDyvlOYo9haZSz5lIcAgb3oC7ko8XlyTJ958bkGFTQJePNO+KFthAn7iSTQ IZR4a4st7xi0qsnwxf+fe+x1Ghr+ds1SjGjp/RMiI6NcW9bbEB6Ap05BAoGBAKx4 sGE+mzd0b0KoZ88rK8SLQ12KsymzVmm4YX3XqGVFNkv7cFRbmtjxTXzaWwtprcAr h/fX3gOe6Im68rMxRBHuwlL6uxz0DFwAAvcmvE9B5CBnmSJXEM9+i9TqMwgNBtV9 AziuvbfOXwmEuwjxaw4ncRKsD7aPtibH0o/rB8udAoGAO8aAeuaMY270MupvdEcr MwESa72xiU2dQB2cGGUIgIqhsD10ZDtoBvmn8qmMAehRXU8LMIbhnb85v9+CJyP2 VwLPZPbuLKQxbInzBFnjpvH5bD2g1g3dJrjVyIj+/26yALSJJBvrcP2j69NCjS+8 Dk7AvdoH/YyVU/uFA89zbTg= -----END PRIVATE KEY-----";
        // 接口参数
        String data = "{ \"kdp_member_number\": \"123\", \"start_date\": \"20200901\", \"end_date\": \"20241130\", \"page\": 1, \"length\": 10, \"key_version\": \"123\", \"sign\": \"123\" }";
        RsaUtils rsaUtils = new RsaUtils(privateKey,"");
        // 使用私钥进行加签
        String sign = rsaUtils.signStr(data);
        System.out.println("sign: " + sign);
        // 使用公钥进行解签
//        boolean isValid = rsaUtils.verifyStr(data, sign);
//        System.out.println("Is valid: " + isValid);
    }
}
