package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_customs_offices_standing_level {
    @JsonProperty("bad")
    bad("bad"),
    @JsonProperty("excellent")
    excellent("excellent"),
    @JsonProperty("good")
    good("good"),
    @JsonProperty("neutral")
    neutral("neutral"),
    @JsonProperty("terrible")
    terrible("terrible");
    public final String toString;

    get_corporations_corporation_id_customs_offices_standing_level(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
