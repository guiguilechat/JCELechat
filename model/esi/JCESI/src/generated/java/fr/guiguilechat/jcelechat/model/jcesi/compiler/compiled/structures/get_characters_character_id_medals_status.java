package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_medals_status {
    @JsonProperty("public")
    _public("public"),
    @JsonProperty("private")
    _private("private");
    public final String toString;

    get_characters_character_id_medals_status(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
