package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Angel space
 */
public class DistributionIDAngel04
    extends IntAttribute
{
    public static final DistributionIDAngel04 INSTANCE = new DistributionIDAngel04();

    @Override
    public int getId() {
        return  1698;
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
        return "DistributionIDAngel04";
    }
}
