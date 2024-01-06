package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This is the total number of Drones controlled by this Entity that can be active in space at any given time.
 */
public class NpcDroneBandwidth
    extends IntAttribute
{
    public static final NpcDroneBandwidth INSTANCE = new NpcDroneBandwidth();

    @Override
    public int getId() {
        return  2785;
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
        return "NpcDroneBandwidth";
    }
}
