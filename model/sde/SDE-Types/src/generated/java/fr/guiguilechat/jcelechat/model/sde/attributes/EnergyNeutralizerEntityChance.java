package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Chance of NPC effect to be activated each duration
 */
public class EnergyNeutralizerEntityChance
    extends DoubleAttribute
{
    public static final EnergyNeutralizerEntityChance INSTANCE = new EnergyNeutralizerEntityChance();

    @Override
    public int getId() {
        return  931;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EnergyNeutralizerEntityChance";
    }
}
