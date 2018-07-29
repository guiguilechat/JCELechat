package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Shield EM Damage Resistance
 */
public class FighterAbilityEvasiveManeuversEmResonance
    extends DoubleAttribute
{
    public final static FighterAbilityEvasiveManeuversEmResonance INSTANCE = new FighterAbilityEvasiveManeuversEmResonance();

    @Override
    public int getId() {
        return  2118;
    }

    @Override
    public int getCatId() {
        return  34;
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
