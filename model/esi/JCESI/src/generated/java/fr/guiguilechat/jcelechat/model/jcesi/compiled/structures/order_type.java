package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum order_type {
    @JsonProperty("buy")
    buy("buy"),
    @JsonProperty("sell")
    sell("sell"),
    @JsonProperty("all")
    all("all");
    public final String toString;

    order_type(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
