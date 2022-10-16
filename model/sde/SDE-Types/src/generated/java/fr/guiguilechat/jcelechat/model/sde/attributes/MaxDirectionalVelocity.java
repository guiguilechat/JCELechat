package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Deprecated.
 */
public class MaxDirectionalVelocity
    extends IntAttribute
{
    public static final MaxDirectionalVelocity INSTANCE = new MaxDirectionalVelocity();

    @Override
    public int getId() {
        return  661;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "MaxDirectionalVelocity";
    }
}
