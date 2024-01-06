package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Just for the UI to display the ship warp speed.
 */
public class BaseWarpSpeed
    extends IntAttribute
{
    public static final BaseWarpSpeed INSTANCE = new BaseWarpSpeed();

    @Override
    public int getId() {
        return  1281;
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
        return true;
    }

    @Override
    public String toString() {
        return "BaseWarpSpeed";
    }
}
