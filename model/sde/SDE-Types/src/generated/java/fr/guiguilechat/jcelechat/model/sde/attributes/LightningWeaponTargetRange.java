package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum distance between two possible targets for the structure doomsday.
 */
public class LightningWeaponTargetRange
    extends IntAttribute
{
    public static final LightningWeaponTargetRange INSTANCE = new LightningWeaponTargetRange();

    @Override
    public int getId() {
        return  2105;
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
        return "LightningWeaponTargetRange";
    }
}
