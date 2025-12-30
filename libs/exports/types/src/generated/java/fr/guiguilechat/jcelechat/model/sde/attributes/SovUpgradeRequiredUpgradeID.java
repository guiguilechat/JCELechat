package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The typeID of the upgrade that is required for this upgrade to be installed. 
 */
public class SovUpgradeRequiredUpgradeID
    extends IntAttribute
{
    public static final SovUpgradeRequiredUpgradeID INSTANCE = new SovUpgradeRequiredUpgradeID();

    @Override
    public int getId() {
        return  1599;
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
