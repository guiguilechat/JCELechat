package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_contracts_type {
    @JsonProperty("unknown")
    unknown("unknown"),
    @JsonProperty("item_exchange")
    item_exchange("item_exchange"),
    @JsonProperty("auction")
    auction("auction"),
    @JsonProperty("courier")
    courier("courier"),
    @JsonProperty("loan")
    loan("loan");
    public final String toString;

    get_characters_character_id_contracts_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
