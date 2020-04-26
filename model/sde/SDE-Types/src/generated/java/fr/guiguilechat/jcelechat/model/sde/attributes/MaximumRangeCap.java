package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum possible target range.
 */
public class MaximumRangeCap
    extends IntAttribute
{
    public static final MaximumRangeCap INSTANCE = new MaximumRangeCap();

    @Override
    public int getId() {
        return  797;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  300000.0;
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
        return "MaximumRangeCap";
    }
}
