package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Sansha space
 */
public class DistributionIDSansha04
    extends IntAttribute
{
    public static final DistributionIDSansha04 INSTANCE = new DistributionIDSansha04();

    @Override
    public int getId() {
        return  1738;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "DistributionIDSansha04";
    }
}
