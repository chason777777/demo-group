import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.net.URLDecoder;

/**
 * @author lichao
 * @version 1.0
 * @Description
 * @date 2019/11/26
 */
public class Fastjson {
    public static void main(String[] args) {
        String jsonStr = "{\"a\":\"\\x";
        try {
            JSON.parse(jsonStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
