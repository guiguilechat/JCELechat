package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum number of ships that can be jumped per activation
 */
public class MjdShipJumpCap
    extends IntAttribute
{
    public static final MjdShipJumpCap INSTANCE = new MjdShipJumpCap();

    @Override
    public int getId() {
        return  2832;
    }

    @Override
    public int getCatId() {
        return  17;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "MjdShipJumpCap";
    }
}
