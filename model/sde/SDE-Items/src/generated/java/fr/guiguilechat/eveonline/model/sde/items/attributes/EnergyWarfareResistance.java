package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Resistance against Energy Neutralizing and Nosferatu
 */
public class EnergyWarfareResistance
    extends DoubleAttribute
{
    public final static EnergyWarfareResistance INSTANCE = new EnergyWarfareResistance();

    @Override
    public int getId() {
        return  2045;
    }

    @Override
    public int getCatId() {
        return  36;
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
        return false;
    }
}
