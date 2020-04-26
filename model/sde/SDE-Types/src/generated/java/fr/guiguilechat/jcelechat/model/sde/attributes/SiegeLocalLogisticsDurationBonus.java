package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Armor Repairer / Shield Booster Duration Bonus
 */
public class SiegeLocalLogisticsDurationBonus
    extends IntAttribute
{
    public static final SiegeLocalLogisticsDurationBonus INSTANCE = new SiegeLocalLogisticsDurationBonus();

    @Override
    public int getId() {
        return  2346;
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
        return "SiegeLocalLogisticsDurationBonus";
    }
}
