package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The amount of charge used from the capacitor for a module activation.
 */
public class CapacitorNeed
    extends DoubleAttribute
{
    public static final CapacitorNeed INSTANCE = new CapacitorNeed();

    @Override
    public int getId() {
        return  6;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "CapacitorNeed";
    }
}
