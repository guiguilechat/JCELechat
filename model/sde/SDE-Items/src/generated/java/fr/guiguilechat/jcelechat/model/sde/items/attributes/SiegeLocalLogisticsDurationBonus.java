package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Armor Repairer / Shield Booster Duration Bonus
 */
public class SiegeLocalLogisticsDurationBonus
    extends IntAttribute
{
    public final static SiegeLocalLogisticsDurationBonus INSTANCE = new SiegeLocalLogisticsDurationBonus();

    @Override
    public int getId() {
        return  2346;
    }

    @Override
    public int getCatId() {
        return  37;
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
