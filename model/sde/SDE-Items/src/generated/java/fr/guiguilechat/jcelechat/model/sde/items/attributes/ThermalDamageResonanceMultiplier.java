package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Multipler to adjust the thermal damage resonance of an object.
 */
public class ThermalDamageResonanceMultiplier
    extends DoubleAttribute
{
    public static final ThermalDamageResonanceMultiplier INSTANCE = new ThermalDamageResonanceMultiplier();

    @Override
    public int getId() {
        return  130;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "ThermalDamageResonanceMultiplier";
    }
}
