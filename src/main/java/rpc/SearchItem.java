package rpc;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;
import external.TicketMasterAPI;
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
import java.util.Set;

/**
 * @author Thoffy
 * @date 2019/9/25 9:50
 */


@WebServlet("/search")
public class SearchItem extends HttpServlet {

    private static final long serialVersionUID = 1L;


    public SearchItem(){
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double lat = Double.parseDouble(request.getParameter("lat"));
        double lon = Double.parseDouble(request.getParameter("lon"));
        String keyword = request.getParameter("term");
        String userId = request.getParameter("user_id");

        DBConnection conn = DBConnectionFactory.getConnection();
        List<Item> items = conn.searchItems(lat,lon,keyword);

        JSONArray array = new JSONArray();
        Set<String> favorite = conn.getFavoriteItemIds(userId);

        try{
            for (Item item : items){
                JSONObject obj = item.toJSONObject();
                obj.put("favorite",favorite.contains(item.getItemId()));
                array.put(obj);
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        RpcHelper.writeJsonArray(response,array);
        conn.close();
    }


}
