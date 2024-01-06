package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_mail_recipient_type {
    @JsonProperty("alliance")
    alliance("alliance"),
    @JsonProperty("character")
    character("character"),
    @JsonProperty("corporation")
    corporation("corporation"),
    @JsonProperty("mailing_list")
    mailing_list("mailing_list");
    public final String toString;

    get_characters_character_id_mail_recipient_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
