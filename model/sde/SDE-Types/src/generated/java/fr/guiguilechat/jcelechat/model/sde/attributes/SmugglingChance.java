package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Chance of being caught Transporting contraband. 
 */
public class SmugglingChance
    extends DoubleAttribute
{
    public static final SmugglingChance INSTANCE = new SmugglingChance();

    @Override
    public int getId() {
        return  445;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SmugglingChance";
    }
}
