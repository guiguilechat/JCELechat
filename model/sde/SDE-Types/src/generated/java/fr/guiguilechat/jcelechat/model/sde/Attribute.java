package fr.guiguilechat.jcelechat.model.sde;

public abstract class Attribute {

    public abstract int getId();

    public abstract int getCatId();

    public abstract boolean getHighIsGood();

    public abstract double getDefaultValue();

    public abstract boolean getPublished();

    public abstract boolean getStackable();

    public Number value(EveType Type) {
        return Type.attribute(this);
    }
}
