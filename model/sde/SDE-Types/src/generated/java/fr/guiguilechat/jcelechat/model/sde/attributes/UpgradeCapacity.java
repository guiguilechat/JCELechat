package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Attribute on ships used for ship upgrades
 */
public class UpgradeCapacity
    extends IntAttribute
{
    public static final UpgradeCapacity INSTANCE = new UpgradeCapacity();

    @Override
    public int getId() {
        return  1132;
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
        return "UpgradeCapacity";
    }
}
