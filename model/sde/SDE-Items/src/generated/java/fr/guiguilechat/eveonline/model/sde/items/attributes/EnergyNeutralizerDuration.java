package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Duration of NPC Energy Neutralizer effect
 */
public class EnergyNeutralizerDuration
    extends IntAttribute
{
    public final static EnergyNeutralizerDuration INSTANCE = new EnergyNeutralizerDuration();

    @Override
    public int getId() {
        return  942;
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
}
