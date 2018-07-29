package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Chance of NPC effect to be activated each duration
 */
public class EnergyNeutralizerEntityChance
    extends IntAttribute
{
    public final static EnergyNeutralizerEntityChance INSTANCE = new EnergyNeutralizerEntityChance();

    @Override
    public int getId() {
        return  931;
    }

    @Override
    public int getCatId() {
        return  22;
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
