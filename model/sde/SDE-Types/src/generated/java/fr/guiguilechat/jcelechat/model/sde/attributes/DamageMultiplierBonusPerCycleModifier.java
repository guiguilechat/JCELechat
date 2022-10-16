package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Modifier for Triglavian ramp-up value
 */
public class DamageMultiplierBonusPerCycleModifier
    extends RealAttribute
{
    public static final DamageMultiplierBonusPerCycleModifier INSTANCE = new DamageMultiplierBonusPerCycleModifier();

    @Override
    public int getId() {
        return  2824;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "DamageMultiplierBonusPerCycleModifier";
    }
}
