package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus attribute for armor repair amount.
 */
public class ArmorDamageAmountBonus
    extends IntAttribute
{
    public static final ArmorDamageAmountBonus INSTANCE = new ArmorDamageAmountBonus();

    @Override
    public int getId() {
        return  895;
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
        return "ArmorDamageAmountBonus";
    }
}
