package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_industry_jobs_status {
    @JsonProperty("active")
    active("active"),
    @JsonProperty("cancelled")
    cancelled("cancelled"),
    @JsonProperty("delivered")
    delivered("delivered"),
    @JsonProperty("paused")
    paused("paused"),
    @JsonProperty("ready")
    ready("ready"),
    @JsonProperty("reverted")
    reverted("reverted");
    public final String toString;

    get_corporations_corporation_id_industry_jobs_status(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
