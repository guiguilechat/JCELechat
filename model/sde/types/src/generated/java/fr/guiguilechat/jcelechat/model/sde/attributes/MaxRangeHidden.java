package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * maxRangeHidden
 */
public class MaxRangeHidden
    extends IntAttribute
{
    public static final MaxRangeHidden INSTANCE = new MaxRangeHidden();

    @Override
    public int getId() {
        return  1317;
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
        return "MaxRangeHidden";
    }
}
