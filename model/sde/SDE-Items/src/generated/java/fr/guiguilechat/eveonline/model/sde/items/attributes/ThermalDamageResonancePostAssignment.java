package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Sets Thermal damage taken by Hull. 
 */
public class ThermalDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ThermalDamageResonancePostAssignment INSTANCE = new ThermalDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2089;
    }

    @Override
    public int getCatId() {
        return  4;
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
        return "ThermalDamageResonancePostAssignment";
    }
}
