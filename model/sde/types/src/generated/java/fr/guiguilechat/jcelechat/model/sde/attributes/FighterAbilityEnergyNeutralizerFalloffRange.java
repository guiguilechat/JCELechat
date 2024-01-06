package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Effectiveness Falloff
 */
public class FighterAbilityEnergyNeutralizerFalloffRange
    extends IntAttribute
{
    public static final FighterAbilityEnergyNeutralizerFalloffRange INSTANCE = new FighterAbilityEnergyNeutralizerFalloffRange();

    @Override
    public int getId() {
        return  2210;
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
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityEnergyNeutralizerFalloffRange";
    }
}
