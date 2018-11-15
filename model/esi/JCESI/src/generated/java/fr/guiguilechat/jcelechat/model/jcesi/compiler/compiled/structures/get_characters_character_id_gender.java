package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_gender {
    @JsonProperty("female")
    female("female"),
    @JsonProperty("male")
    male("male");
    public final String toString;

    get_characters_character_id_gender(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
