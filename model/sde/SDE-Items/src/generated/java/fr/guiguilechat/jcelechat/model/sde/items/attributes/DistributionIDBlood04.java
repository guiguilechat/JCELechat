package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Distribution ID for sov upgrades in Blood Raider space
 */
public class DistributionIDBlood04
    extends IntAttribute
{
    public final static DistributionIDBlood04 INSTANCE = new DistributionIDBlood04();

    @Override
    public int getId() {
        return  1708;
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
        return "DistributionIDBlood04";
    }
}
