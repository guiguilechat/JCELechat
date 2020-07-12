package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Shield Explosive Damage Resistance
 */
public class FighterAbilityEvasiveManeuversExpResonance
    extends DoubleAttribute
{
    public static final FighterAbilityEvasiveManeuversExpResonance INSTANCE = new FighterAbilityEvasiveManeuversExpResonance();

    @Override
    public int getId() {
        return  2121;
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
        return "FighterAbilityEvasiveManeuversExpResonance";
    }
}