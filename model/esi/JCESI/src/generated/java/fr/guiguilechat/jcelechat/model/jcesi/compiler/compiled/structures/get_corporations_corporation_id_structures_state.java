package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_structures_state {
    @JsonProperty("anchor_vulnerable")
    anchor_vulnerable("anchor_vulnerable"),
    @JsonProperty("anchoring")
    anchoring("anchoring"),
    @JsonProperty("armor_reinforce")
    armor_reinforce("armor_reinforce"),
    @JsonProperty("armor_vulnerable")
    armor_vulnerable("armor_vulnerable"),
    @JsonProperty("fitting_invulnerable")
    fitting_invulnerable("fitting_invulnerable"),
    @JsonProperty("hull_reinforce")
    hull_reinforce("hull_reinforce"),
    @JsonProperty("hull_vulnerable")
    hull_vulnerable("hull_vulnerable"),
    @JsonProperty("online_deprecated")
    online_deprecated("online_deprecated"),
    @JsonProperty("onlining_vulnerable")
    onlining_vulnerable("onlining_vulnerable"),
    @JsonProperty("shield_vulnerable")
    shield_vulnerable("shield_vulnerable"),
    @JsonProperty("unanchored")
    unanchored("unanchored"),
    @JsonProperty("unknown")
    unknown("unknown");
    public final String toString;

    get_corporations_corporation_id_structures_state(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
