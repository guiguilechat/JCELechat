package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for global sov upgrades
 */
public class DistributionID01
    extends IntAttribute
{
    public static final DistributionID01 INSTANCE = new DistributionID01();

    @Override
    public int getId() {
        return  1755;
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
        return "DistributionID01";
    }
}
