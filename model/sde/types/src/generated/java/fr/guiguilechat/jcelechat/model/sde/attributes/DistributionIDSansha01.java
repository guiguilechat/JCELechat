package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Sansha space
 */
public class DistributionIDSansha01
    extends IntAttribute
{
    public static final DistributionIDSansha01 INSTANCE = new DistributionIDSansha01();

    @Override
    public int getId() {
        return  1735;
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
        return "DistributionIDSansha01";
    }
}
