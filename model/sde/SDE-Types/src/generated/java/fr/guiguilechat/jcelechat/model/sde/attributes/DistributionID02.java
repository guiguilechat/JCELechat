package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for global sov upgrades
 */
public class DistributionID02
    extends IntAttribute
{
    public static final DistributionID02 INSTANCE = new DistributionID02();

    @Override
    public int getId() {
        return  1756;
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
        return "DistributionID02";
    }
}
