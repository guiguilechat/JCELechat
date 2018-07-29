package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Effectiveness Falloff
 */
public class FighterAbilityEnergyNeutralizerFalloffRange
    extends IntAttribute
{
    public final static FighterAbilityEnergyNeutralizerFalloffRange INSTANCE = new FighterAbilityEnergyNeutralizerFalloffRange();

    @Override
    public int getId() {
        return  2210;
    }

    @Override
    public int getCatId() {
        return  34;
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
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityEnergyNeutralizerFalloffRange";
    }
}
