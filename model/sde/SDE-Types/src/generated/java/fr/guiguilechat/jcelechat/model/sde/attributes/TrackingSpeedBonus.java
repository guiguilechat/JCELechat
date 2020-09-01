package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Tracking Speed Bonus
 */
public class TrackingSpeedBonus
    extends RealAttribute
{
    public static final TrackingSpeedBonus INSTANCE = new TrackingSpeedBonus();

    @Override
    public int getId() {
        return  767;
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
        return "TrackingSpeedBonus";
    }
}
