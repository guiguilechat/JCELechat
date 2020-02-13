package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusStrategicCruiserGallente2
    extends IntAttribute
{
    public static final ShipBonusStrategicCruiserGallente2 INSTANCE = new ShipBonusStrategicCruiserGallente2();

    @Override
    public int getId() {
        return  2678;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "ShipBonusStrategicCruiserGallente2";
    }
}
