package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_industry_systems_activity {
    @JsonProperty("copying")
    copying("copying"),
    @JsonProperty("duplicating")
    duplicating("duplicating"),
    @JsonProperty("invention")
    invention("invention"),
    @JsonProperty("manufacturing")
    manufacturing("manufacturing"),
    @JsonProperty("none")
    none("none"),
    @JsonProperty("reaction")
    reaction("reaction"),
    @JsonProperty("researching_material_efficiency")
    researching_material_efficiency("researching_material_efficiency"),
    @JsonProperty("researching_technology")
    researching_technology("researching_technology"),
    @JsonProperty("researching_time_efficiency")
    researching_time_efficiency("researching_time_efficiency"),
    @JsonProperty("reverse_engineering")
    reverse_engineering("reverse_engineering");
    public final String toString;

    get_industry_systems_activity(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
