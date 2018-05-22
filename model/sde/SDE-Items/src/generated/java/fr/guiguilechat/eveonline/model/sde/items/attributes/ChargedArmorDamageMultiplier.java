package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Multiplier applied to armorDamageAmount when consuming a collection of charges.
 */
public class ChargedArmorDamageMultiplier
    extends IntAttribute
{
    public final static ChargedArmorDamageMultiplier INSTANCE = new ChargedArmorDamageMultiplier();

    @Override
    public int getId() {
        return  1886;
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
        return "ChargedArmorDamageMultiplier";
    }
}
