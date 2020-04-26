package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Shield Thermal Damage Resistance
 */
public class FighterAbilityEvasiveManeuversThermResonance
    extends DoubleAttribute
{
    public static final FighterAbilityEvasiveManeuversThermResonance INSTANCE = new FighterAbilityEvasiveManeuversThermResonance();

    @Override
    public int getId() {
        return  2119;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityEvasiveManeuversThermResonance";
    }
}
