package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Time bonus for Refinery Structures
 */
public class StrReactionTimeMultiplier
    extends RealAttribute
{
    public static final StrReactionTimeMultiplier INSTANCE = new StrReactionTimeMultiplier();

    @Override
    public int getId() {
        return  2721;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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

    @Override
    public String toString() {
        return "StrReactionTimeMultiplier";
    }
}
