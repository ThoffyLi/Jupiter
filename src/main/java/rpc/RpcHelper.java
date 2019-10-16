package rpc;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * @author Thoffy
 * @date 2019/9/29 15:34
 */
public class RpcHelper {

    // Writes a JSONArray to http response.
    public static void writeJsonArray(HttpServletResponse response, JSONArray array) {
        try {
            response.setContentType("application/json");
            response.addHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            out.print(array);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Writes a JSONObject to http response.
    public static void writeJsonObject(HttpServletResponse response, JSONObject obj) {
        try {
            response.setContentType("application/json");
            response.addHeader("Access-Control-Allow-Origin", "*");
            PrintWriter out = response.getWriter();
            out.print(obj);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static JSONObject readJsonObject(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            reader.close();
            return new JSONObject(sb.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
