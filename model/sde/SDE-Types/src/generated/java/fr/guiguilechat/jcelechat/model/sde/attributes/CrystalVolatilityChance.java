package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * The chance of damage to the crystal each time it is used.
 */
public class CrystalVolatilityChance
    extends DoubleAttribute
{
    public static final CrystalVolatilityChance INSTANCE = new CrystalVolatilityChance();

    @Override
    public int getId() {
        return  783;
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
        return "CrystalVolatilityChance";
    }
}
