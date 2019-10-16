package algorithm;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;

import java.util.*;
import java.util.Map.Entry;

/**
 * @author Thoffy
 * @date 2019/9/30 18:40
 */
public class GeoRecommendation {

    public List<Item> recommendItems(String userId, double lat, double lon){
        List<Item> recommendedItems = new ArrayList<>();
        DBConnection conn = DBConnectionFactory.getConnection();

        // Step 1 Get all favorite item ids
        Set<String> favoriteItemIds = conn.getFavoriteItemIds(userId);

        // Step 2 Get all categories of favorite items, sort by count
        Map<String,Integer> allCategories = new HashMap<>();
        for (String itemId : favoriteItemIds){
            Set<String> categories = conn.getCategories(itemId);
            for (String category : categories){
                if (allCategories.containsKey(category)){
                    allCategories.put(category,allCategories.get(category) + 1);
                }
                else {
                    allCategories.put(category,1);
                }
            }
        }

        List<Entry<String,Integer>> categoryList =
                new ArrayList<Entry<String, Integer>>(allCategories.entrySet());

        Collections.sort(categoryList, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return Integer.compare(o2.getValue(),o1.getValue());
            }
        });

        // Step 3, do search based on category, filter out favorited events, sort by distance
        Set<Item> visitedItems = new HashSet<>();

        for (Entry<String, Integer> category : categoryList){
            List<Item> items = conn.searchItems(lat, lon, category.getKey());
            List<Item> filteredItems = new ArrayList<>();

            for (Item item : items){
                if (!favoriteItemIds.contains(item.getItemId()) && !visitedItems.contains(item)){
                    filteredItems.add(item);
                }
            }

            Collections.sort(filteredItems, new Comparator<Item>() {
                @Override
                public int compare(Item o1, Item o2) {
                    return Double.compare(o1.getDistance(),o2.getDistance());
                }
            });

            visitedItems.addAll(items);
            recommendedItems.addAll(filteredItems);
        }
        return recommendedItems;
    }

}
