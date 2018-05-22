package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Chance of being caught selling contraband on market. 
 */
public class ContrabandFencingChance
    extends DoubleAttribute
{
    public final static ContrabandFencingChance INSTANCE = new ContrabandFencingChance();

    @Override
    public int getId() {
        return  444;
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
        return "ContrabandFencingChance";
    }
}
