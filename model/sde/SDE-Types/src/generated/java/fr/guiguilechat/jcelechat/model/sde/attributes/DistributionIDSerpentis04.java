package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Serpentis space
 */
public class DistributionIDSerpentis04
    extends IntAttribute
{
    public static final DistributionIDSerpentis04 INSTANCE = new DistributionIDSerpentis04();

    @Override
    public int getId() {
        return  1748;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "DistributionIDSerpentis04";
    }
}
