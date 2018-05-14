package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * An amount to modify the power of the target by.
 */
public class EnergyNeutralizerAmount
    extends IntAttribute
{
    public final static EnergyNeutralizerAmount INSTANCE = new EnergyNeutralizerAmount();

    @Override
    public int getId() {
        return  97;
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
}
