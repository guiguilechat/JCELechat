package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Maximum amount of Reactions slots that can be used at a time
 */
public class ReactionSlotLimit
    extends IntAttribute
{
    public final static ReactionSlotLimit INSTANCE = new ReactionSlotLimit();

    @Override
    public int getId() {
        return  2664;
    }

    @Override
    public int getCatId() {
        return  9;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
