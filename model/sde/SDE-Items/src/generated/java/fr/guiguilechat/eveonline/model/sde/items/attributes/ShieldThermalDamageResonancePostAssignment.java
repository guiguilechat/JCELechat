package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Sets Thermal damage taken by Shields. 
 */
public class ShieldThermalDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ShieldThermalDamageResonancePostAssignment INSTANCE = new ShieldThermalDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2086;
    }

    @Override
    public int getCatId() {
        return  2;
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
}
