package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration of NPC Energy Neutralizer effect
 */
public class EnergyNeutralizerDuration
    extends IntAttribute
{
    public static final EnergyNeutralizerDuration INSTANCE = new EnergyNeutralizerDuration();

    @Override
    public int getId() {
        return  942;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  30000.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EnergyNeutralizerDuration";
    }
}
