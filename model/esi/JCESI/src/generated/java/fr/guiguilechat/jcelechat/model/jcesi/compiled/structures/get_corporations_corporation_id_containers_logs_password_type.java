package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_corporations_corporation_id_containers_logs_password_type {
    @JsonProperty("config")
    config("config"),
    @JsonProperty("general")
    general("general");
    public final String toString;

    get_corporations_corporation_id_containers_logs_password_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
