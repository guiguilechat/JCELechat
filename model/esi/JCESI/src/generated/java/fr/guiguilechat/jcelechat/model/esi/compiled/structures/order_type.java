package fr.guiguilechat.jcelechat.model.esi.compiled.structures;

public enum order_type {
    buy("buy"),
    sell("sell"),
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
