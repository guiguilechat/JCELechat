package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Shield Kinetic Damage Resistance
 */
public class FighterAbilityEvasiveManeuversKinResonance
    extends RealAttribute
{
    public static final FighterAbilityEvasiveManeuversKinResonance INSTANCE = new FighterAbilityEvasiveManeuversKinResonance();

    @Override
    public int getId() {
        return  2120;
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
        return "FighterAbilityEvasiveManeuversKinResonance";
    }
}
