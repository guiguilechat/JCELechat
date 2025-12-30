package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class MaxFOFTargetRange
    extends IntAttribute
{
    public static final MaxFOFTargetRange INSTANCE = new MaxFOFTargetRange();

    @Override
    public int getId() {
        return  2700;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  200000.0;
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
        return "MaxFOFTargetRange";
    }
}
