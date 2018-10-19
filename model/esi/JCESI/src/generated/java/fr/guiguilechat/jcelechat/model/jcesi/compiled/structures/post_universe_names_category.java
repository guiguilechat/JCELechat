package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum post_universe_names_category {
    @JsonProperty("alliance")
    alliance("alliance"),
    @JsonProperty("character")
    character("character"),
    @JsonProperty("constellation")
    constellation("constellation"),
    @JsonProperty("corporation")
    corporation("corporation"),
    @JsonProperty("inventory_type")
    inventory_type("inventory_type"),
    @JsonProperty("region")
    region("region"),
    @JsonProperty("solar_system")
    solar_system("solar_system"),
    @JsonProperty("station")
    station("station");
    public final String toString;

    post_universe_names_category(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
