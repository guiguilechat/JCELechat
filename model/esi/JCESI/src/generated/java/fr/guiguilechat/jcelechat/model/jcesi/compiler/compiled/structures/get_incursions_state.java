package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_incursions_state {
    @JsonProperty("withdrawing")
    withdrawing("withdrawing"),
    @JsonProperty("mobilizing")
    mobilizing("mobilizing"),
    @JsonProperty("established")
    established("established");
    public final String toString;

    get_incursions_state(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
