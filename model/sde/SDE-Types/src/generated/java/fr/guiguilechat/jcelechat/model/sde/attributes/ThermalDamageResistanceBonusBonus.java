package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ThermalDamageResistanceBonusBonus
    extends IntAttribute
{
    public static final ThermalDamageResistanceBonusBonus INSTANCE = new ThermalDamageResistanceBonusBonus();

    @Override
    public int getId() {
        return  2405;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "ThermalDamageResistanceBonusBonus";
    }
}
