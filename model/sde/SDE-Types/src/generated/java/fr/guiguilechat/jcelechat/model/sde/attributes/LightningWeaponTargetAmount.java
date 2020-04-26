package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of targets affected by the structure doomsday beam.
 */
public class LightningWeaponTargetAmount
    extends IntAttribute
{
    public static final LightningWeaponTargetAmount INSTANCE = new LightningWeaponTargetAmount();

    @Override
    public int getId() {
        return  2104;
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
        return "LightningWeaponTargetAmount";
    }
}
