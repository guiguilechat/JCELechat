package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class ShipBonusStrategicCruiserAmarr2
    extends IntAttribute
{
    public static final ShipBonusStrategicCruiserAmarr2 INSTANCE = new ShipBonusStrategicCruiserAmarr2();

    @Override
    public int getId() {
        return  2677;
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
        return "ShipBonusStrategicCruiserAmarr2";
    }
}
