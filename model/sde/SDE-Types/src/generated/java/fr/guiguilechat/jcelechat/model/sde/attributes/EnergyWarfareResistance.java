package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Resistance against Energy Neutralizing and Nosferatu
 */
public class EnergyWarfareResistance
    extends RealAttribute
{
    public static final EnergyWarfareResistance INSTANCE = new EnergyWarfareResistance();

    @Override
    public int getId() {
        return  2045;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  1.0;
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
        return "EnergyWarfareResistance";
    }
}
