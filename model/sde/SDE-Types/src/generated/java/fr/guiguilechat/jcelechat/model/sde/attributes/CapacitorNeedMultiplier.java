package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Scales the capacitor need for fitted modules.
 */
public class CapacitorNeedMultiplier
    extends DoubleAttribute
{
    public static final CapacitorNeedMultiplier INSTANCE = new CapacitorNeedMultiplier();

    @Override
    public int getId() {
        return  216;
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
        return "CapacitorNeedMultiplier";
    }
}