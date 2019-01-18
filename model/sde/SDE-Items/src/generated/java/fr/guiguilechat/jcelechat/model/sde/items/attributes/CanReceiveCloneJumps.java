package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Defines whether a ship has the functionality to allow it to receive clone jumps and host jump clones.
 */
public class CanReceiveCloneJumps
    extends IntAttribute
{
    public static final CanReceiveCloneJumps INSTANCE = new CanReceiveCloneJumps();

    @Override
    public int getId() {
        return  982;
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
        return "CanReceiveCloneJumps";
    }
}
