package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * modifies warp bubble immuntiy 
 */
public class WarpBubbleImmuneBonus
    extends IntAttribute
{
    public static final WarpBubbleImmuneBonus INSTANCE = new WarpBubbleImmuneBonus();

    @Override
    public int getId() {
        return  3120;
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
        return false;
    }

    @Override
    public String toString() {
        return "WarpBubbleImmuneBonus";
    }
}
