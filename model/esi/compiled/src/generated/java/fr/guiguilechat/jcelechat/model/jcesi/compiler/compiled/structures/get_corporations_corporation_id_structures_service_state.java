package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_structures_service_state {
    @JsonProperty("online")
    online("online"),
    @JsonProperty("offline")
    offline("offline"),
    @JsonProperty("cleanup")
    cleanup("cleanup");
    public final String toString;

    get_corporations_corporation_id_structures_service_state(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
