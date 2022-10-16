package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Survey Scanner Range Bonus
 */
public class SurveyScannerRangeBonus
    extends IntAttribute
{
    public static final SurveyScannerRangeBonus INSTANCE = new SurveyScannerRangeBonus();

    @Override
    public int getId() {
        return  1234;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "SurveyScannerRangeBonus";
    }
}
