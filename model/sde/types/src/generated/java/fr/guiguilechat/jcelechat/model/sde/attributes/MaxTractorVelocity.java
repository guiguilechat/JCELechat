package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum speed that the tractor beam draws objects closer at.
 */
public class MaxTractorVelocity
    extends IntAttribute
{
    public static final MaxTractorVelocity INSTANCE = new MaxTractorVelocity();

    @Override
    public int getId() {
        return  1045;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MaxTractorVelocity";
    }
}
