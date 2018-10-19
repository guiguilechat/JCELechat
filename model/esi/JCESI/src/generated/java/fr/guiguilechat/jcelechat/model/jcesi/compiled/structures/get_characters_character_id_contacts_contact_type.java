package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_contacts_contact_type {
    @JsonProperty("character")
    character("character"),
    @JsonProperty("corporation")
    corporation("corporation"),
    @JsonProperty("alliance")
    alliance("alliance"),
    @JsonProperty("faction")
    faction("faction");
    public final String toString;

    get_characters_character_id_contacts_contact_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
