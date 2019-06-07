package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_wallet_journal_context_id_type {
    @JsonProperty("structure_id")
    structure_id("structure_id"),
    @JsonProperty("station_id")
    station_id("station_id"),
    @JsonProperty("market_transaction_id")
    market_transaction_id("market_transaction_id"),
    @JsonProperty("character_id")
    character_id("character_id"),
    @JsonProperty("corporation_id")
    corporation_id("corporation_id"),
    @JsonProperty("alliance_id")
    alliance_id("alliance_id"),
    @JsonProperty("eve_system")
    eve_system("eve_system"),
    @JsonProperty("industry_job_id")
    industry_job_id("industry_job_id"),
    @JsonProperty("contract_id")
    contract_id("contract_id"),
    @JsonProperty("planet_id")
    planet_id("planet_id"),
    @JsonProperty("system_id")
    system_id("system_id"),
    @JsonProperty("type_id")
    type_id("type_id");
    public final String toString;

    get_characters_character_id_wallet_journal_context_id_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
