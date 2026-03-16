package rahulshetty.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtils {
    /*Get json data and convert it to List of HashMaps
    public static List<HashMap<String,String>> getJsonDataToMapByTestName(String filePath, String testName) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jo = new JSONObject(jsonContent);
        JSONArray array = jo.getJSONArray(testName);
        List<HashMap<String,String>> data = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            HashMap<String,String> map = new HashMap<>();
            obj.keySet().forEach(key -> map.put(key, obj.getString(key)));
            data.add(map);
        }
        return data;
    }*/


    public static String getBaseURL(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jo = new JSONObject(jsonContent);
        return jo.getString("baseURL");
    }

    // Add this method used by BaseTest
    public static List<HashMap<String,String>> getJsonDataToMapByEnv(String filePath, String env, String testName) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jo = new JSONObject(jsonContent);

        if (!jo.has(env)) {
            throw new RuntimeException("Env key not found in JSON: " + env);
        }
        JSONObject envData = jo.getJSONObject(env);
        if (!envData.has(testName)) {
            throw new RuntimeException("Test data key not found in env JSON: " + testName);
        }

        JSONArray array = envData.getJSONArray(testName);
        List<HashMap<String,String>> data = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            HashMap<String,String> map = new HashMap<>();
            obj.keySet().forEach(key -> map.put(key, obj.getString(key)));
            //obj.entrySet().forEach((key, v) -> map.put(key, v.toString()));
            data.add(map);
        }
        return data;
    }
}
