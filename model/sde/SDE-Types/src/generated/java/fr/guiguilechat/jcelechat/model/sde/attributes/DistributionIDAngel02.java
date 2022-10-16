package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Angel space
 */
public class DistributionIDAngel02
    extends IntAttribute
{
    public static final DistributionIDAngel02 INSTANCE = new DistributionIDAngel02();

    @Override
    public int getId() {
        return  1696;
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
        return "DistributionIDAngel02";
    }
}
