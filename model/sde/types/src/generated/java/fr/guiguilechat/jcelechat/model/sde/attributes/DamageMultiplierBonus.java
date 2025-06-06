package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Autogenerated skill attribute, damageMultiplierBonus
 */
public class DamageMultiplierBonus
    extends RealAttribute
{
    public static final DamageMultiplierBonus INSTANCE = new DamageMultiplierBonus();

    @Override
    public int getId() {
        return  292;
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
        return "DamageMultiplierBonus";
    }
}
