package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Thermal resistance bonus for shields
 */
public class ShieldThermalDamageResistanceBonus
    extends IntAttribute
{
    public final static ShieldThermalDamageResistanceBonus INSTANCE = new ShieldThermalDamageResistanceBonus();

    @Override
    public int getId() {
        return  1492;
    }

    @Override
    public int getCatId() {
        return  2;
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
        return "ShieldThermalDamageResistanceBonus";
    }
}
