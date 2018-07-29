package fr.guiguilechat.jcelechat.model.sde.items;

public abstract class Attribute {

    public abstract int getId();

    public abstract int getCatId();

    public abstract boolean getHighIsGood();

    public abstract double getDefaultValue();

    public abstract boolean getPublished();

    public abstract boolean getStackable();

    public Number value(Item item) {
        return item.attribute(this);
    }
}
