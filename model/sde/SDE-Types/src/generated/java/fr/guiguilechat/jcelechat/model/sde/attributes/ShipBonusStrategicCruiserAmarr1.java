package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusStrategicCruiserAmarr1
    extends IntAttribute
{
    public static final ShipBonusStrategicCruiserAmarr1 INSTANCE = new ShipBonusStrategicCruiserAmarr1();

    @Override
    public int getId() {
        return  1503;
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
        return "ShipBonusStrategicCruiserAmarr1";
    }
}
