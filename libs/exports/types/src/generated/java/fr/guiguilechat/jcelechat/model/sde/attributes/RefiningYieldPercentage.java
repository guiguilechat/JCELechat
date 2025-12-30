package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Refining yield percentage
 */
public class RefiningYieldPercentage
    extends IntAttribute
{
    public static final RefiningYieldPercentage INSTANCE = new RefiningYieldPercentage();

    @Override
    public int getId() {
        return  378;
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
        return "RefiningYieldPercentage";
    }
}
