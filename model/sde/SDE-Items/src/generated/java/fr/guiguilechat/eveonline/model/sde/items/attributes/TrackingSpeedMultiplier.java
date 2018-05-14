package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Scale the tracking speed of a weapon.
 */
public class TrackingSpeedMultiplier
    extends DoubleAttribute
{
    public final static TrackingSpeedMultiplier INSTANCE = new TrackingSpeedMultiplier();

    @Override
    public int getId() {
        return  244;
    }

    @Override
    public int getCatId() {
        return  26;
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
}
