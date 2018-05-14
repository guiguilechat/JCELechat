package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Time bonus for Refinery Structures
 */
public class StrReactionTimeMultiplier
    extends DoubleAttribute
{
    public final static StrReactionTimeMultiplier INSTANCE = new StrReactionTimeMultiplier();

    @Override
    public int getId() {
        return  2721;
    }

    @Override
    public int getCatId() {
        return  4;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
