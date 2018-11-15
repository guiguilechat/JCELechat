package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum flag {
    @JsonProperty("shortest")
    shortest("shortest"),
    @JsonProperty("secure")
    secure("secure"),
    @JsonProperty("insecure")
    insecure("insecure");
    public final String toString;

    flag(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
