package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The typeID of the upgrade that is required for this upgrade to be installed. 
 */
public class SovUpgradeRequiredUpgradeID
    extends IntAttribute
{
    public final static SovUpgradeRequiredUpgradeID INSTANCE = new SovUpgradeRequiredUpgradeID();

    @Override
    public int getId() {
        return  1599;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SovUpgradeRequiredUpgradeID";
    }
}
