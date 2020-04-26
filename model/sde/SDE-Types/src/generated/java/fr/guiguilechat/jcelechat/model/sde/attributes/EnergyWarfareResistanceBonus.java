package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class EnergyWarfareResistanceBonus
    extends IntAttribute
{
    public static final EnergyWarfareResistanceBonus INSTANCE = new EnergyWarfareResistanceBonus();

    @Override
    public int getId() {
        return  2267;
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
        return true;
    }

    @Override
    public String toString() {
        return "EnergyWarfareResistanceBonus";
    }
}
