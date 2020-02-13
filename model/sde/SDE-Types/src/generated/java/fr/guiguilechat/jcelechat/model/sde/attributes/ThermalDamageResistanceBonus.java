package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class ThermalDamageResistanceBonus
    extends DoubleAttribute
{
    public static final ThermalDamageResistanceBonus INSTANCE = new ThermalDamageResistanceBonus();

    @Override
    public int getId() {
        return  987;
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
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "ThermalDamageResistanceBonus";
    }
}
