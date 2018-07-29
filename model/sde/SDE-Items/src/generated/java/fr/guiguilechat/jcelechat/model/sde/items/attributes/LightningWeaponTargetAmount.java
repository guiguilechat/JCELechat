package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Number of targets affected by the structure doomsday beam.
 */
public class LightningWeaponTargetAmount
    extends IntAttribute
{
    public final static LightningWeaponTargetAmount INSTANCE = new LightningWeaponTargetAmount();

    @Override
    public int getId() {
        return  2104;
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
        return "LightningWeaponTargetAmount";
    }
}
