package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_shareholders_shareholder_type {
    @JsonProperty("character")
    character("character"),
    @JsonProperty("corporation")
    corporation("corporation");
    public final String toString;

    get_corporations_corporation_id_shareholders_shareholder_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
