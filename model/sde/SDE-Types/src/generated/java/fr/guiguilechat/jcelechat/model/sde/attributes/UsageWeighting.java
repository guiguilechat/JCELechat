package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The weighting given to this type and its chance of being picked for a grouping.
 */
public class UsageWeighting
    extends RealAttribute
{
    public static final UsageWeighting INSTANCE = new UsageWeighting();

    @Override
    public int getId() {
        return  862;
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
        return "UsageWeighting";
    }
}
