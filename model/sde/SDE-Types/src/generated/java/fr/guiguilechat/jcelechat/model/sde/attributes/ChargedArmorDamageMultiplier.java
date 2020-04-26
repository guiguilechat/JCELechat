package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Multiplier applied to armorDamageAmount when consuming a collection of charges.
 */
public class ChargedArmorDamageMultiplier
    extends IntAttribute
{
    public static final ChargedArmorDamageMultiplier INSTANCE = new ChargedArmorDamageMultiplier();

    @Override
    public int getId() {
        return  1886;
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
