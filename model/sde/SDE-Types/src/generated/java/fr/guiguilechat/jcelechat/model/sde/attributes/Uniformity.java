package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
 */
public class Uniformity
    extends RealAttribute
{
    public static final Uniformity INSTANCE = new Uniformity();

    @Override
    public int getId() {
        return  136;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "Uniformity";
    }
}
