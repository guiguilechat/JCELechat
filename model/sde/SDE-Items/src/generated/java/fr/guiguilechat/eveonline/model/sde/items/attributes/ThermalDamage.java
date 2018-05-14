package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Thermal damage done.
 */
public class ThermalDamage
    extends DoubleAttribute
{
    public final static ThermalDamage INSTANCE = new ThermalDamage();

    @Override
    public int getId() {
        return  118;
    }

    @Override
    public int getCatId() {
        return  29;
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
