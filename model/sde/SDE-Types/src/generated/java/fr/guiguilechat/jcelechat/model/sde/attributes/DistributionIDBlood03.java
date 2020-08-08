package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Blood Raider space
 */
public class DistributionIDBlood03
    extends IntAttribute
{
    public static final DistributionIDBlood03 INSTANCE = new DistributionIDBlood03();

    @Override
    public int getId() {
        return  1707;
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
        return "DistributionIDBlood03";
    }
}
