package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to trackingSpeedBonus
 */
public class TrackingSpeedBonusBonus
    extends IntAttribute
{
    public static final TrackingSpeedBonusBonus INSTANCE = new TrackingSpeedBonusBonus();

    @Override
    public int getId() {
        return  1316;
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
        return "TrackingSpeedBonusBonus";
    }
}
