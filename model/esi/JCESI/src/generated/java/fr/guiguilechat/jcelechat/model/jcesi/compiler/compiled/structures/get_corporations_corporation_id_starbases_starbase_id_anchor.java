package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_starbases_starbase_id_anchor {
    @JsonProperty("alliance_member")
    alliance_member("alliance_member"),
    @JsonProperty("config_starbase_equipment_role")
    config_starbase_equipment_role("config_starbase_equipment_role"),
    @JsonProperty("corporation_member")
    corporation_member("corporation_member"),
    @JsonProperty("starbase_fuel_technician_role")
    starbase_fuel_technician_role("starbase_fuel_technician_role");
    public final String toString;

    get_corporations_corporation_id_starbases_starbase_id_anchor(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
