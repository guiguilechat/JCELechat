package com.okyk.eveonline.zkb;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ZKBFilter {

    public static final String LIMIT = "limit";
    private static String BASE_URL = "https://zkillboard.com/api/";

    public static String STATS = "stats";
    public static String CHAR = "character";
    public static String CHAR_ID = "characterID";
    public static String CORP_ID = "corporationID";
    public static String ALLY_ID = "allianceID";
    public static String FACTION_ID = "factionID";
    public static String SHIP_TYPE_ID = "shipTypeID";
    public static String GROUP_ID = "groupID";
    public static String SOLAR_SYSTEM_ID = "solarSystemID";
    public static String REGION_ID = "regionID";

    public static final String PAST_SECONDS = "pastSeconds";

    private String prefix = "";
    private String entityType = "";
    private LinkedHashSet<String> entityID = new LinkedHashSet<>();
    private LinkedHashSet<String> modifier = new LinkedHashSet<>();

    public ZKBFilter(String entityType, Long... entityID) {
        this.entityType = entityType;
        addEntityID(entityID);
    }

    public ZKBFilter setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public ZKBFilter addEntityID(Long... entityID) {
        this.entityID.addAll(Arrays.stream(entityID)
                .map(Object::toString)
                .collect(Collectors.toList()));
        return this;
    }

    public ZKBFilter modifier(String... modifier) {
        this.modifier.addAll(Arrays.asList(modifier));
        return this;
    }

    public String build() {
        final StringBuilder builder = new StringBuilder(BASE_URL);
        if (!prefix.isEmpty()) {
            builder.append(prefix).append("/");
        }
        builder.append(entityType).append("/")
                .append(entityID.stream().collect(Collectors.joining(","))).append("/");
        if (!modifier.isEmpty()) {
            builder.append(modifier.stream().collect(Collectors.joining("/"))).append("/");
        }
        return builder.toString();
    }
}
