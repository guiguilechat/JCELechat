package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * The amount of charge used from the capacitor for a module activation.
 */
public class CapacitorNeed
    extends DoubleAttribute
{
    public final static CapacitorNeed INSTANCE = new CapacitorNeed();

    @Override
    public int getId() {
        return  6;
    }

    @Override
    public int getCatId() {
        return  5;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
