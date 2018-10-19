package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_starbases_state {
    @JsonProperty("offline")
    offline("offline"),
    @JsonProperty("online")
    online("online"),
    @JsonProperty("onlining")
    onlining("onlining"),
    @JsonProperty("reinforced")
    reinforced("reinforced"),
    @JsonProperty("unanchoring")
    unanchoring("unanchoring");
    public final String toString;

    get_corporations_corporation_id_starbases_state(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
