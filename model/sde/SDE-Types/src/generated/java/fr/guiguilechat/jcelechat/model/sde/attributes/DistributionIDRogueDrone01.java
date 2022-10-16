package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distribution ID for sov upgrades in Rogue Drone space
 */
public class DistributionIDRogueDrone01
    extends IntAttribute
{
    public static final DistributionIDRogueDrone01 INSTANCE = new DistributionIDRogueDrone01();

    @Override
    public int getId() {
        return  1725;
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
        return "DistributionIDRogueDrone01";
    }
}
