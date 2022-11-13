package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * skill bonus attribute3 for gallente battleship
 */
public class ShipBonusGB3
    extends RealAttribute
{
    public static final ShipBonusGB3 INSTANCE = new ShipBonusGB3();

    @Override
    public int getId() {
        return  5240;
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
        return "ShipBonusGB3";
    }
}
