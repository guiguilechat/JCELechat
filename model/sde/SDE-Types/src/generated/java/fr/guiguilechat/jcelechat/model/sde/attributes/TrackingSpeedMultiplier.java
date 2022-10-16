package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Scale the tracking speed of a weapon.
 */
public class TrackingSpeedMultiplier
    extends RealAttribute
{
    public static final TrackingSpeedMultiplier INSTANCE = new TrackingSpeedMultiplier();

    @Override
    public int getId() {
        return  244;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
        return "TrackingSpeedMultiplier";
    }
}
