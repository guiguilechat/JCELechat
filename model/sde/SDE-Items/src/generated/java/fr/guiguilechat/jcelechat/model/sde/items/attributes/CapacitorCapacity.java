package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Capacitor capacity
 */
public class CapacitorCapacity
    extends DoubleAttribute
{
    public static final CapacitorCapacity INSTANCE = new CapacitorCapacity();

    @Override
    public int getId() {
        return  482;
    }

    @Override
    public int getCatId() {
        return  5;
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
        return "CapacitorCapacity";
    }
}
