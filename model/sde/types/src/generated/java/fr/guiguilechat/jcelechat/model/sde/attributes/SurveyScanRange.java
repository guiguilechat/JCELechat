package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distance from thing to survey.
 */
public class SurveyScanRange
    extends IntAttribute
{
    public static final SurveyScanRange INSTANCE = new SurveyScanRange();

    @Override
    public int getId() {
        return  197;
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
        return "SurveyScanRange";
    }
}
