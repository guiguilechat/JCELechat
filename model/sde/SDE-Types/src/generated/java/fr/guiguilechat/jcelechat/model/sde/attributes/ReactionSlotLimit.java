package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum amount of Reactions slots that can be used at a time
 */
public class ReactionSlotLimit
    extends IntAttribute
{
    public static final ReactionSlotLimit INSTANCE = new ReactionSlotLimit();

    @Override
    public int getId() {
        return  2664;
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
        return "ReactionSlotLimit";
    }
}
