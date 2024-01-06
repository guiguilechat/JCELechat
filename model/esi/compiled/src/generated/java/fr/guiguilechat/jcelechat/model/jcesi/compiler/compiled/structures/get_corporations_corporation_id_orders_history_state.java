package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_orders_history_state {
    @JsonProperty("cancelled")
    cancelled("cancelled"),
    @JsonProperty("expired")
    expired("expired");
    public final String toString;

    get_corporations_corporation_id_orders_history_state(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
