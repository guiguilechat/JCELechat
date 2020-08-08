package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Sansha space
 */
public class DistributionIDSansha02
    extends IntAttribute
{
    public static final DistributionIDSansha02 INSTANCE = new DistributionIDSansha02();

    @Override
    public int getId() {
        return  1736;
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
        return "DistributionIDSansha02";
    }
}
