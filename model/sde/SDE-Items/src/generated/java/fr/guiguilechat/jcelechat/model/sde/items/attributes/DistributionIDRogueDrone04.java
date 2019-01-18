package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Distribution ID for sov upgrades in Rogue Drone space
 */
public class DistributionIDRogueDrone04
    extends IntAttribute
{
    public static final DistributionIDRogueDrone04 INSTANCE = new DistributionIDRogueDrone04();

    @Override
    public int getId() {
        return  1728;
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
        return "DistributionIDRogueDrone04";
    }
}
