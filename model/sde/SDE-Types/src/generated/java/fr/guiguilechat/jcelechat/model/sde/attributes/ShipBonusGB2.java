package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * skill bonus attribute2 for gallente battleship
 */
public class ShipBonusGB2
    extends RealAttribute
{
    public static final ShipBonusGB2 INSTANCE = new ShipBonusGB2();

    @Override
    public int getId() {
        return  561;
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
        return "ShipBonusGB2";
    }
}
