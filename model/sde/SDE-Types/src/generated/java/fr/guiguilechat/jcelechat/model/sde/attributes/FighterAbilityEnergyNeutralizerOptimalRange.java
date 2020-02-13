package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Optimal Range
 */
public class FighterAbilityEnergyNeutralizerOptimalRange
    extends IntAttribute
{
    public static final FighterAbilityEnergyNeutralizerOptimalRange INSTANCE = new FighterAbilityEnergyNeutralizerOptimalRange();

    @Override
    public int getId() {
        return  2209;
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
        return "FighterAbilityEnergyNeutralizerOptimalRange";
    }
}
