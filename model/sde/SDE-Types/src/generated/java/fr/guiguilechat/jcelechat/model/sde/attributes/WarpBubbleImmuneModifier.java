package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class WarpBubbleImmuneModifier
    extends IntAttribute
{
    public static final WarpBubbleImmuneModifier INSTANCE = new WarpBubbleImmuneModifier();

    @Override
    public int getId() {
        return  1539;
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
        return "WarpBubbleImmuneModifier";
    }
}
