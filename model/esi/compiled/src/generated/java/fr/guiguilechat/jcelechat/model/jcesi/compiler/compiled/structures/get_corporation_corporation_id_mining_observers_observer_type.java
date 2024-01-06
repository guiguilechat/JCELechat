package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporation_corporation_id_mining_observers_observer_type {
    @JsonProperty("structure")
    structure("structure");
    public final String toString;

    get_corporation_corporation_id_mining_observers_observer_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
