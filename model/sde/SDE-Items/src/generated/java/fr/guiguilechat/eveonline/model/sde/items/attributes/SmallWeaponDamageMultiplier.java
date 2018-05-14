package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Damage multiplier for small weapons
 */
public class SmallWeaponDamageMultiplier
    extends DoubleAttribute
{
    public final static SmallWeaponDamageMultiplier INSTANCE = new SmallWeaponDamageMultiplier();

    @Override
    public int getId() {
        return  1493;
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
}
