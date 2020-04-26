package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum distance at which the object can be used.
 */
public class MaxOperationalDistance
    extends IntAttribute
{
    public static final MaxOperationalDistance INSTANCE = new MaxOperationalDistance();

    @Override
    public int getId() {
        return  715;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MaxOperationalDistance";
    }
}
