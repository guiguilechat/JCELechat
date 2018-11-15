package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_notifications_sender_type {
    @JsonProperty("character")
    character("character"),
    @JsonProperty("corporation")
    corporation("corporation"),
    @JsonProperty("alliance")
    alliance("alliance"),
    @JsonProperty("faction")
    faction("faction"),
    @JsonProperty("other")
    other("other");
    public final String toString;

    get_characters_character_id_notifications_sender_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
