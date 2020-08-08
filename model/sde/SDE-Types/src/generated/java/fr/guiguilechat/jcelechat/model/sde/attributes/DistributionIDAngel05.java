package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Angel space
 */
public class DistributionIDAngel05
    extends IntAttribute
{
    public static final DistributionIDAngel05 INSTANCE = new DistributionIDAngel05();

    @Override
    public int getId() {
        return  1699;
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
        return "DistributionIDAngel05";
    }
}
