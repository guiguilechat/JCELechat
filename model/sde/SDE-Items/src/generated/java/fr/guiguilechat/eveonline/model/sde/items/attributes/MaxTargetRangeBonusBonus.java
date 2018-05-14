package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus to maxTargetRangeBonus
 */
public class MaxTargetRangeBonusBonus
    extends IntAttribute
{
    public final static MaxTargetRangeBonusBonus INSTANCE = new MaxTargetRangeBonusBonus();

    @Override
    public int getId() {
        return  1313;
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
}
