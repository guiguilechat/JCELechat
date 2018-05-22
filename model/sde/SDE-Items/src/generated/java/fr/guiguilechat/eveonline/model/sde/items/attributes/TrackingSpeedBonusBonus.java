package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus to trackingSpeedBonus
 */
public class TrackingSpeedBonusBonus
    extends IntAttribute
{
    public final static TrackingSpeedBonusBonus INSTANCE = new TrackingSpeedBonusBonus();

    @Override
    public int getId() {
        return  1316;
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
        return "TrackingSpeedBonusBonus";
    }
}
