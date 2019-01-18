package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Minimum distance where a starbase structure can be anchored at from the control tower shield extremity in meters.
 */
public class ControlTowerMinimumDistance
    extends IntAttribute
{
    public static final ControlTowerMinimumDistance INSTANCE = new ControlTowerMinimumDistance();

    @Override
    public int getId() {
        return  1165;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "ControlTowerMinimumDistance";
    }
}
