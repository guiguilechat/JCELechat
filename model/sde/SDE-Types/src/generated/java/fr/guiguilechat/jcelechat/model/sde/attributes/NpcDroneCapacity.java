package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This is the total number of Drones that the Entity has in it's cargo hold ready to be deployed. Once it has deployed this many drones, then it no longer has any more.
 */
public class NpcDroneCapacity
    extends IntAttribute
{
    public static final NpcDroneCapacity INSTANCE = new NpcDroneCapacity();

    @Override
    public int getId() {
        return  2784;
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
        return "NpcDroneCapacity";
    }
}
