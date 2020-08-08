package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusStrategicCruiserCaldari1
    extends IntAttribute
{
    public static final ShipBonusStrategicCruiserCaldari1 INSTANCE = new ShipBonusStrategicCruiserCaldari1();

    @Override
    public int getId() {
        return  1504;
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
