package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Capacitor capacity
 */
public class CapacitorCapacity
    extends RealAttribute
{
    public static final CapacitorCapacity INSTANCE = new CapacitorCapacity();

    @Override
    public int getId() {
        return  482;
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
        return "CapacitorCapacity";
    }
}
