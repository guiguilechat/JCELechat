package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Bonus to maxRangeBonus
 */
public class MaxRangeBonusBonus
    extends IntAttribute
{
    public final static MaxRangeBonusBonus INSTANCE = new MaxRangeBonusBonus();

    @Override
    public int getId() {
        return  1315;
    }

    @Override
    public int getCatId() {
        return  6;
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
        return "MaxRangeBonusBonus";
    }
}
