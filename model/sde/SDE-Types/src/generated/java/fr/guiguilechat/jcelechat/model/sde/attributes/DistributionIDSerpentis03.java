package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Serpentis space
 */
public class DistributionIDSerpentis03
    extends IntAttribute
{
    public static final DistributionIDSerpentis03 INSTANCE = new DistributionIDSerpentis03();

    @Override
    public int getId() {
        return  1747;
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
        return "DistributionIDSerpentis03";
    }
}
