package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Additional percentage to the characters missile damage multiplier.
 */
public class MissileDamageMultiplierBonus
    extends DoubleAttribute
{
    public static final MissileDamageMultiplierBonus INSTANCE = new MissileDamageMultiplierBonus();

    @Override
    public int getId() {
        return  213;
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
        return "MissileDamageMultiplierBonus";
    }
}
