package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_standings_from_type {
    @JsonProperty("agent")
    agent("agent"),
    @JsonProperty("npc_corp")
    npc_corp("npc_corp"),
    @JsonProperty("faction")
    faction("faction");
    public final String toString;

    get_characters_character_id_standings_from_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
