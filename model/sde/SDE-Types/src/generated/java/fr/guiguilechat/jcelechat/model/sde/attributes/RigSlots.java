package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The number of rig slots on the ship.
 */
public class RigSlots
    extends IntAttribute
{
    public static final RigSlots INSTANCE = new RigSlots();

    @Override
    public int getId() {
        return  1137;
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
        return "RigSlots";
    }
}
