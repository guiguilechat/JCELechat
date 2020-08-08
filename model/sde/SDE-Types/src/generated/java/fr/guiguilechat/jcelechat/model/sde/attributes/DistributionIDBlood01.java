package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Blood Raider space
 */
public class DistributionIDBlood01
    extends IntAttribute
{
    public static final DistributionIDBlood01 INSTANCE = new DistributionIDBlood01();

    @Override
    public int getId() {
        return  1705;
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
        return "DistributionIDBlood01";
    }
}
