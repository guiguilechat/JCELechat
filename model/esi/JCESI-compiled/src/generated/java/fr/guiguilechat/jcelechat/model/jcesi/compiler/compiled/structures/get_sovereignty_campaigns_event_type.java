package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_sovereignty_campaigns_event_type {
    @JsonProperty("tcu_defense")
    tcu_defense("tcu_defense"),
    @JsonProperty("ihub_defense")
    ihub_defense("ihub_defense"),
    @JsonProperty("station_defense")
    station_defense("station_defense"),
    @JsonProperty("station_freeport")
    station_freeport("station_freeport");
    public final String toString;

    get_sovereignty_campaigns_event_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
