package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Distribution ID for sov upgrades in Blood Raider space
 */
public class DistributionIDBlood05
    extends IntAttribute
{
    public final static DistributionIDBlood05 INSTANCE = new DistributionIDBlood05();

    @Override
    public int getId() {
        return  1709;
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
        return "DistributionIDBlood05";
    }
}
