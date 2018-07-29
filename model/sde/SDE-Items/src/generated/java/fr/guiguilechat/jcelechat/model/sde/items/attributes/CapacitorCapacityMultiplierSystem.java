package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Capacitor capacity multiplier
 */
public class CapacitorCapacityMultiplierSystem
    extends DoubleAttribute
{
    public final static CapacitorCapacityMultiplierSystem INSTANCE = new CapacitorCapacityMultiplierSystem();

    @Override
    public int getId() {
        return  1499;
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
        return "CapacitorCapacityMultiplierSystem";
    }
}
