package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_medals_issued_status {
    @JsonProperty("private")
    _private("private"),
    @JsonProperty("public")
    _public("public");
    public final String toString;

    get_corporations_corporation_id_medals_issued_status(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
