package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Falloff Range of Energy Neutralizer
 */
public class EnergyNeutralizerRangeFalloff
    extends IntAttribute
{
    public static final EnergyNeutralizerRangeFalloff INSTANCE = new EnergyNeutralizerRangeFalloff();

    @Override
    public int getId() {
        return  2452;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "EnergyNeutralizerRangeFalloff";
    }
}
