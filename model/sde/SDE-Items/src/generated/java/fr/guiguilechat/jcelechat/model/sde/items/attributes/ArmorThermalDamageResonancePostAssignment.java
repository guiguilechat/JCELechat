package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Sets Thermal damage taken by Armor. 
 */
public class ArmorThermalDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ArmorThermalDamageResonancePostAssignment INSTANCE = new ArmorThermalDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2082;
    }

    @Override
    public int getCatId() {
        return  3;
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
        return "ArmorThermalDamageResonancePostAssignment";
    }
}
