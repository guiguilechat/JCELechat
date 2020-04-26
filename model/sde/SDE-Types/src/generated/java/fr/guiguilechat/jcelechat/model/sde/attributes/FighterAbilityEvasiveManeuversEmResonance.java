package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Shield EM Damage Resistance
 */
public class FighterAbilityEvasiveManeuversEmResonance
    extends DoubleAttribute
{
    public static final FighterAbilityEvasiveManeuversEmResonance INSTANCE = new FighterAbilityEvasiveManeuversEmResonance();

    @Override
    public int getId() {
        return  2118;
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
        return "FighterAbilityEvasiveManeuversEmResonance";
    }
}
