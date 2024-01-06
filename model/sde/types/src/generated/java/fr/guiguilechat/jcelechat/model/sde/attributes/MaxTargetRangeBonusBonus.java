package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to maxTargetRangeBonus
 */
public class MaxTargetRangeBonusBonus
    extends IntAttribute
{
    public static final MaxTargetRangeBonusBonus INSTANCE = new MaxTargetRangeBonusBonus();

    @Override
    public int getId() {
        return  1313;
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
        return "MaxTargetRangeBonusBonus";
    }
}
