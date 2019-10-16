package rpc;

import algorithm.GeoRecommendation;
import clojure.lang.IFn;
import entity.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author Thoffy
 * @date 2019/9/25 10:56
 */
@WebServlet("/recommend")
public class RecommendItem extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public RecommendItem(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("user_id");
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lon = Double.parseDouble(request.getParameter("lon"));

        GeoRecommendation recommendation = new GeoRecommendation();
        List<Item> items = recommendation.recommendItems(userId,lat,lon);

        JSONArray result = new JSONArray();
        try {
            for (Item item : items){
                result.put(item.toJSONObject());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        RpcHelper.writeJsonArray(response,result);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
