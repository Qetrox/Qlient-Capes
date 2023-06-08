package net.qlient.capes.util;

import com.google.gson.*;
import net.qlient.capes.client.qlient;

import java.io.IOException;

public class VersionChecker {

    private static final String version = qlient.VERSION;
    public static String latestversion = null;

    public static boolean isLatest() {
        if (latestversion == null) {
            return true;
        }

        int latestVersion1 = Integer.parseInt(latestversion.replace(".", ""));
        int version1 = Integer.parseInt(version.replace(".", ""));
        if(latestVersion1 > version1) {
            return false;
        }

        return true;
    }

    public static String getVersion() {
        return latestversion;
    }

    public static void getOnlineVersion() {
        try {
            String response = HttpsUtil.sendGETSSL("https://qlient.net/api/data/");
            Gson gson = new GsonBuilder().create();
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);

            JsonArray versionsArray = jsonObject.getAsJsonArray("versions");

            JsonObject modsObject = versionsArray.get(0).getAsJsonObject();
            JsonArray modsArray = modsObject.getAsJsonArray("mods");

            JsonObject qlientcapesObject = null;
            for (JsonElement modElement : modsArray) {
                JsonObject modObject = modElement.getAsJsonObject();
                if (modObject.has("qlientcapes")) {
                    qlientcapesObject = modObject;
                    break;
                }
            }

            if (qlientcapesObject != null) {
                latestversion = qlientcapesObject.get("qlientcapes").getAsString();
            } else {
                latestversion = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            latestversion = null;
        }
    }

}
