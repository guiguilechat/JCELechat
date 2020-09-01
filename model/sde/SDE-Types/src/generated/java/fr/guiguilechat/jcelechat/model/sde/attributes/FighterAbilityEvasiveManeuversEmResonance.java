package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Shield EM Damage Resistance
 */
public class FighterAbilityEvasiveManeuversEmResonance
    extends RealAttribute
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
    public Number getDefaultValue() {
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
