package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Thermal damage resistance bonus for armor
 */
public class ArmorThermalDamageResistanceBonus
    extends IntAttribute
{
    public final static ArmorThermalDamageResistanceBonus INSTANCE = new ArmorThermalDamageResistanceBonus();

    @Override
    public int getId() {
        return  1467;
    }

    @Override
    public int getCatId() {
        return  3;
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
        return "ArmorThermalDamageResistanceBonus";
    }
}
