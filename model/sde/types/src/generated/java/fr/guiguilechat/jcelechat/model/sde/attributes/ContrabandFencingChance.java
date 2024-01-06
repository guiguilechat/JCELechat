package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Chance of being caught selling contraband on market. 
 */
public class ContrabandFencingChance
    extends RealAttribute
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
    public Number getDefaultValue() {
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
