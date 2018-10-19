package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_calendar_event_response {
    @JsonProperty("declined")
    declined("declined"),
    @JsonProperty("not_responded")
    not_responded("not_responded"),
    @JsonProperty("accepted")
    accepted("accepted"),
    @JsonProperty("tentative")
    tentative("tentative");
    public final String toString;

    get_characters_character_id_calendar_event_response(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
