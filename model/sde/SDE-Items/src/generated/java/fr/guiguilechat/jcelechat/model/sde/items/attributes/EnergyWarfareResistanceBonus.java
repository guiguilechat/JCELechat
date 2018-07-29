package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * 
 */
public class EnergyWarfareResistanceBonus
    extends IntAttribute
{
    public final static EnergyWarfareResistanceBonus INSTANCE = new EnergyWarfareResistanceBonus();

    @Override
    public int getId() {
        return  2267;
    }

    @Override
    public int getCatId() {
        return  36;
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
        return "EnergyWarfareResistanceBonus";
    }
}
