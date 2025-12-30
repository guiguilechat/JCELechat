package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Armor Repairer / Shield Booster Capacitor Need Bonus
 */
public class SiegeLocalLogisticsCapacitorNeedBonus
    extends IntAttribute
{
    public static final SiegeLocalLogisticsCapacitorNeedBonus INSTANCE = new SiegeLocalLogisticsCapacitorNeedBonus();

    @Override
    public int getId() {
        return  5988;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "SiegeLocalLogisticsCapacitorNeedBonus";
    }
}
