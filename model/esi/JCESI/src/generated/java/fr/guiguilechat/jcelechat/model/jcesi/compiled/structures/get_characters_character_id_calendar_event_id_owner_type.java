package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_calendar_event_id_owner_type {
    @JsonProperty("eve_server")
    eve_server("eve_server"),
    @JsonProperty("corporation")
    corporation("corporation"),
    @JsonProperty("faction")
    faction("faction"),
    @JsonProperty("character")
    character("character"),
    @JsonProperty("alliance")
    alliance("alliance");
    public final String toString;

    get_characters_character_id_calendar_event_id_owner_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
