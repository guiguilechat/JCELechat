package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class ShipBonusStrategicCruiserCaldari1
    extends IntAttribute
{
    public final static ShipBonusStrategicCruiserCaldari1 INSTANCE = new ShipBonusStrategicCruiserCaldari1();

    @Override
    public int getId() {
        return  1504;
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
        return "ShipBonusStrategicCruiserCaldari1";
    }
}
