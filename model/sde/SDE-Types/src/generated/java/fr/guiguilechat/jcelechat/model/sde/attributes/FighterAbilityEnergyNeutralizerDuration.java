package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration
 */
public class FighterAbilityEnergyNeutralizerDuration
    extends IntAttribute
{
    public static final FighterAbilityEnergyNeutralizerDuration INSTANCE = new FighterAbilityEnergyNeutralizerDuration();

    @Override
    public int getId() {
        return  2208;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "FighterAbilityEnergyNeutralizerDuration";
    }
}
