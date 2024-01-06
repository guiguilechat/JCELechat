package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Thermal damage done.
 */
public class ThermalDamage
    extends RealAttribute
{
    public static final ThermalDamage INSTANCE = new ThermalDamage();

    @Override
    public int getId() {
        return  118;
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
        return "ThermalDamage";
    }
}
