package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Optimal Range of Energy Neutralizer
 */
public class EnergyNeutralizerRangeOptimal
    extends IntAttribute
{
    public final static EnergyNeutralizerRangeOptimal INSTANCE = new EnergyNeutralizerRangeOptimal();

    @Override
    public int getId() {
        return  98;
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
        return "EnergyNeutralizerRangeOptimal";
    }
}
