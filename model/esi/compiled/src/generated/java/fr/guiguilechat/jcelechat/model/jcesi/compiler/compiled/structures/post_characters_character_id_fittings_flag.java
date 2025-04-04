package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum post_characters_character_id_fittings_flag {
    @JsonProperty("Cargo")
    Cargo("Cargo"),
    @JsonProperty("DroneBay")
    DroneBay("DroneBay"),
    @JsonProperty("FighterBay")
    FighterBay("FighterBay"),
    @JsonProperty("HiSlot0")
    HiSlot0("HiSlot0"),
    @JsonProperty("HiSlot1")
    HiSlot1("HiSlot1"),
    @JsonProperty("HiSlot2")
    HiSlot2("HiSlot2"),
    @JsonProperty("HiSlot3")
    HiSlot3("HiSlot3"),
    @JsonProperty("HiSlot4")
    HiSlot4("HiSlot4"),
    @JsonProperty("HiSlot5")
    HiSlot5("HiSlot5"),
    @JsonProperty("HiSlot6")
    HiSlot6("HiSlot6"),
    @JsonProperty("HiSlot7")
    HiSlot7("HiSlot7"),
    @JsonProperty("Invalid")
    Invalid("Invalid"),
    @JsonProperty("LoSlot0")
    LoSlot0("LoSlot0"),
    @JsonProperty("LoSlot1")
    LoSlot1("LoSlot1"),
    @JsonProperty("LoSlot2")
    LoSlot2("LoSlot2"),
    @JsonProperty("LoSlot3")
    LoSlot3("LoSlot3"),
    @JsonProperty("LoSlot4")
    LoSlot4("LoSlot4"),
    @JsonProperty("LoSlot5")
    LoSlot5("LoSlot5"),
    @JsonProperty("LoSlot6")
    LoSlot6("LoSlot6"),
    @JsonProperty("LoSlot7")
    LoSlot7("LoSlot7"),
    @JsonProperty("MedSlot0")
    MedSlot0("MedSlot0"),
    @JsonProperty("MedSlot1")
    MedSlot1("MedSlot1"),
    @JsonProperty("MedSlot2")
    MedSlot2("MedSlot2"),
    @JsonProperty("MedSlot3")
    MedSlot3("MedSlot3"),
    @JsonProperty("MedSlot4")
    MedSlot4("MedSlot4"),
    @JsonProperty("MedSlot5")
    MedSlot5("MedSlot5"),
    @JsonProperty("MedSlot6")
    MedSlot6("MedSlot6"),
    @JsonProperty("MedSlot7")
    MedSlot7("MedSlot7"),
    @JsonProperty("RigSlot0")
    RigSlot0("RigSlot0"),
    @JsonProperty("RigSlot1")
    RigSlot1("RigSlot1"),
    @JsonProperty("RigSlot2")
    RigSlot2("RigSlot2"),
    @JsonProperty("ServiceSlot0")
    ServiceSlot0("ServiceSlot0"),
    @JsonProperty("ServiceSlot1")
    ServiceSlot1("ServiceSlot1"),
    @JsonProperty("ServiceSlot2")
    ServiceSlot2("ServiceSlot2"),
    @JsonProperty("ServiceSlot3")
    ServiceSlot3("ServiceSlot3"),
    @JsonProperty("ServiceSlot4")
    ServiceSlot4("ServiceSlot4"),
    @JsonProperty("ServiceSlot5")
    ServiceSlot5("ServiceSlot5"),
    @JsonProperty("ServiceSlot6")
    ServiceSlot6("ServiceSlot6"),
    @JsonProperty("ServiceSlot7")
    ServiceSlot7("ServiceSlot7"),
    @JsonProperty("SubSystemSlot0")
    SubSystemSlot0("SubSystemSlot0"),
    @JsonProperty("SubSystemSlot1")
    SubSystemSlot1("SubSystemSlot1"),
    @JsonProperty("SubSystemSlot2")
    SubSystemSlot2("SubSystemSlot2"),
    @JsonProperty("SubSystemSlot3")
    SubSystemSlot3("SubSystemSlot3");
    public final String toString;

    post_characters_character_id_fittings_flag(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
