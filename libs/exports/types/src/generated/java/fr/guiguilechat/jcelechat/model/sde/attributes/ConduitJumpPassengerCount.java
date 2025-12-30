package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * How many passengers can be carried in a Conduit Jump
 */
public class ConduitJumpPassengerCount
    extends IntAttribute
{
    public static final ConduitJumpPassengerCount INSTANCE = new ConduitJumpPassengerCount();

    @Override
    public int getId() {
        return  3133;
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
        return false;
    }

    @Override
    public String toString() {
        return "ConduitJumpPassengerCount";
    }
}
