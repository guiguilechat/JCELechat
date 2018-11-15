package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_contracts_availability {
    @JsonProperty("public")
    _public("public"),
    @JsonProperty("personal")
    personal("personal"),
    @JsonProperty("corporation")
    corporation("corporation"),
    @JsonProperty("alliance")
    alliance("alliance");
    public final String toString;

    get_characters_character_id_contracts_availability(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
