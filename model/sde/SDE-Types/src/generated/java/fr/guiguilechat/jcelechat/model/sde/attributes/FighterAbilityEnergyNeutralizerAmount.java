package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Energy Amount Neutralized
 */
public class FighterAbilityEnergyNeutralizerAmount
    extends IntAttribute
{
    public static final FighterAbilityEnergyNeutralizerAmount INSTANCE = new FighterAbilityEnergyNeutralizerAmount();

    @Override
    public int getId() {
        return  2211;
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
        return "FighterAbilityEnergyNeutralizerAmount";
    }
}
