package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * How much of the upgrade capacity is used when this is fitted to a ship.
 */
public class UpgradeCost
    extends IntAttribute
{
    public static final UpgradeCost INSTANCE = new UpgradeCost();

    @Override
    public int getId() {
        return  1153;
    }

    @Override
    public int getCatId() {
        return  1;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "UpgradeCost";
    }
}
