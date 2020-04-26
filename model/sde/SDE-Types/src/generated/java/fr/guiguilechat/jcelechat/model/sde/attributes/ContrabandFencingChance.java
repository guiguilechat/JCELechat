package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Chance of being caught selling contraband on market. 
 */
public class ContrabandFencingChance
    extends DoubleAttribute
{
    public static final ContrabandFencingChance INSTANCE = new ContrabandFencingChance();

    @Override
    public int getId() {
        return  444;
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
        return "ContrabandFencingChance";
    }
}
