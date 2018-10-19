package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum response {
    @JsonProperty("accepted")
    accepted("accepted"),
    @JsonProperty("declined")
    declined("declined"),
    @JsonProperty("tentative")
    tentative("tentative");
    public final String toString;

    response(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
