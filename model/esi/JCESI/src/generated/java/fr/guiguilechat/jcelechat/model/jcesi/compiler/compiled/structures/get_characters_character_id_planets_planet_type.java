package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_characters_character_id_planets_planet_type {
    @JsonProperty("temperate")
    temperate("temperate"),
    @JsonProperty("barren")
    barren("barren"),
    @JsonProperty("oceanic")
    oceanic("oceanic"),
    @JsonProperty("ice")
    ice("ice"),
    @JsonProperty("gas")
    gas("gas"),
    @JsonProperty("lava")
    lava("lava"),
    @JsonProperty("storm")
    storm("storm"),
    @JsonProperty("plasma")
    plasma("plasma");
    public final String toString;

    get_characters_character_id_planets_planet_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
