package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_fw_systems_contested {
    @JsonProperty("captured")
    captured("captured"),
    @JsonProperty("contested")
    contested("contested"),
    @JsonProperty("uncontested")
    uncontested("uncontested"),
    @JsonProperty("vulnerable")
    vulnerable("vulnerable");
    public final String toString;

    get_fw_systems_contested(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
