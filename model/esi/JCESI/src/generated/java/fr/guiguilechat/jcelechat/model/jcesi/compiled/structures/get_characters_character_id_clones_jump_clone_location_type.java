package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_clones_jump_clone_location_type {
    @JsonProperty("station")
    station("station"),
    @JsonProperty("structure")
    structure("structure");
    public final String toString;

    get_characters_character_id_clones_jump_clone_location_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
