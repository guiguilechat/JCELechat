package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Fixed Role Bonus on a ship.
 */
public class ShipBonusRole7
    extends RealAttribute
{
    public static final ShipBonusRole7 INSTANCE = new ShipBonusRole7();

    @Override
    public int getId() {
        return  793;
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
        return "ShipBonusRole7";
    }
}
