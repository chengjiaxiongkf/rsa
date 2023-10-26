import com.rsa.RsaUtils;

public class AppMain {
    public static void main(String[] args) throws Exception {
        RsaUtils rsaUtils = new RsaUtils();
        String data = "{ \"kdp_member_number\": \"123\", \"start_date\": \"20200901\", \"end_date\": \"20241130\", \"page\": 1, \"length\": 10, \"key_version\": \"123\", \"sign\": \"123\" }";
        // 使用私钥进行加签
        String sign = rsaUtils.signStr(data);
        System.out.println("sign: " + sign);
        // 使用公钥进行解签
//        boolean isValid = rsaUtils.verifyStr(data, sign);
//        System.out.println("Is valid: " + isValid);
    }
}
