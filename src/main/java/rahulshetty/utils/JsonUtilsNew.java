package rahulshetty.utils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class JsonUtilsNew {

    // Fetch data for a given testName (NegativeValidation / PositiveValidation)
    public static List<HashMap<String,String>> getTestData(String testName) throws Exception {

        // 1️⃣ Read env from terminal: qa, uat, etc.
        String env = System.getProperty("env");
        if (env == null) env = "qa"; // default if not passed

        // 2️⃣ Build file name dynamically
        String filePath = System.getProperty("user.dir") + "//src//test//java//rahulshetty//data/Formdata_" + env + ".json";

        JSONParser parser = new JSONParser();

        List<HashMap<String,String>> dataList = new ArrayList<>();

        Object obj = parser.parse(new FileReader(filePath));

        // 3️⃣ Check if JSON is array (multiple datasets) or single object
        if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            for (Object o : arr) {
                JSONObject jo = (JSONObject) o;
                HashMap<String,String> map = new HashMap<>();
                for (Object key : jo.keySet()) {
                    map.put((String) key, (String) jo.get((String) key));
                }
                dataList.add(map);
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jo = (JSONObject) obj;
            HashMap<String,String> map = new HashMap<>();
            for (Object key : jo.keySet()) {
                map.put((String) key, (String) jo.get((String) key));
            }
            dataList.add(map);
        }

        return dataList; // returns all datasets for this test
    }
}