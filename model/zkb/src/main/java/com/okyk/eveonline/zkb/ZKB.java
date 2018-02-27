package com.okyk.eveonline.zkb;

import com.google.gson.*;
import com.okyk.eveonline.zkb.dto.KBObject;
import com.okyk.eveonline.zkb.dto.KBStats;

import java.util.HashMap;

public class ZKB {

    private static final HashMap<String, KBObject[]> zkbCache = new HashMap<String, KBObject[]>();
    private static final HashMap<String, Long> charIDCache = new HashMap<>();

    static KBObject[] parseZKBResponse(String json) {
        try {
            final JsonArray asJsonArray = new JsonParser().parse(json).getAsJsonArray();
            return new Gson().fromJson(asJsonArray, KBObject[].class);
        } catch (IllegalStateException e){
            throw new IllegalStateException(json, e);
        }
    }

    public static KBObject[] loadKB(ZKBFilter filter) {
        try {
            return parseZKBResponse(zkbRequest(filter));
        }catch (IllegalStateException e){
            System.out.println(e.getLocalizedMessage()+"\n for filter"+filter.build());
        }
        return null;
    }

    private static String zkbRequest(ZKBFilter filter) {
        return HttpUtils.connectGet(filter.build(), false);
    }

    public static KBObject[] loadCharKB(long charID, Long timeLimit) {
        final ZKBFilter zkbFilter = new ZKBFilter(ZKBFilter.CHAR, charID);
        if (timeLimit > 0) {
            zkbFilter.modifier(ZKBFilter.PAST_SECONDS, timeLimit.toString());
        }
        return parseZKBResponse(HttpUtils.connectGet(zkbFilter.build(), false));
    }

    public static KBStats loadStats(Long charID) {
        String statURL = new ZKBFilter(ZKBFilter.CHAR_ID, charID).setPrefix(ZKBFilter.STATS).build();
        String json = HttpUtils.connectGet(statURL, false);
        return parseStatResponse(json);
    }

    static KBStats parseStatResponse(String json) {
        final JsonObject asJsonObject = new JsonParser().parse(json).getAsJsonObject();
        return new Gson().fromJson(asJsonObject, KBStats.class);
    }

}