package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Optimal Range of Energy Neutralizer
 */
public class EnergyNeutralizerRangeOptimal
    extends IntAttribute
{
    public static final EnergyNeutralizerRangeOptimal INSTANCE = new EnergyNeutralizerRangeOptimal();

    @Override
    public int getId() {
        return  98;
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
        return true;
    }

    @Override
    public String toString() {
        return "EnergyNeutralizerRangeOptimal";
    }
}
