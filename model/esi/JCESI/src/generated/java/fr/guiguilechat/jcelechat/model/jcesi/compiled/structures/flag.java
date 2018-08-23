package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

public enum flag {
    shortest("shortest"),
    secure("secure"),
    insecure("insecure");
    public final String toString;

    flag(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
