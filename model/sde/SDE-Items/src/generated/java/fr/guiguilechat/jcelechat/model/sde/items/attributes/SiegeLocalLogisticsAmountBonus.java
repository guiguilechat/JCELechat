package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Armor Repairer / Shield Booster Amount Bonus
 */
public class SiegeLocalLogisticsAmountBonus
    extends IntAttribute
{
    public static final SiegeLocalLogisticsAmountBonus INSTANCE = new SiegeLocalLogisticsAmountBonus();

    @Override
    public int getId() {
        return  2347;
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
        return "SiegeLocalLogisticsAmountBonus";
    }
}
