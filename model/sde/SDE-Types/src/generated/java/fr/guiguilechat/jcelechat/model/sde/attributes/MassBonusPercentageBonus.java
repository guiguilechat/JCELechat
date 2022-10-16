package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to massBonusPercentage
 */
public class MassBonusPercentageBonus
    extends IntAttribute
{
    public static final MassBonusPercentageBonus INSTANCE = new MassBonusPercentageBonus();

    @Override
    public int getId() {
        return  1324;
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
        return "MassBonusPercentageBonus";
    }
}
