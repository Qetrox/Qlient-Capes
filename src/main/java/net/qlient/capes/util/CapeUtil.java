package net.qlient.capes.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;
import org.apache.commons.io.FileUtils;
import net.minecraft.client.texture.NativeImage;

public class CapeUtil {

    static Map<String, String> capes = new HashMap<>();

    public static String CheckCape(String UUID) {
        UUID = StringUtil.UUIDWithoutDashes(UUID);
        if (capes.containsKey(UUID)) {
            return capes.get(UUID);
        } else {
            return "NONE";
        }
    }

    public static void DownloadCapes() {
        try {
            capes.clear();
            String response = HttpsUtil.sendGETSSL("https://qlient.net/api/client/capecheck/");
            Gson gson = new GsonBuilder().create();
            JsonArray jsonArray = gson.fromJson(response, JsonArray.class);
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                String uuid = jsonObject.get("uuid").getAsString();
                String capeId = jsonObject.get("Cape_id").getAsString();
                capes.put(uuid, capeId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}