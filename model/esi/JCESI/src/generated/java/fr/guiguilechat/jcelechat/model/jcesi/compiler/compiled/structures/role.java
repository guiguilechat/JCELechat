package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum role {
    @JsonProperty("fleet_commander")
    fleet_commander("fleet_commander"),
    @JsonProperty("wing_commander")
    wing_commander("wing_commander"),
    @JsonProperty("squad_commander")
    squad_commander("squad_commander"),
    @JsonProperty("squad_member")
    squad_member("squad_member");
    public final String toString;

    role(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
