package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Second Stock Bonus on Pirate Faction Ships.
 */
public class ShipBonusRole8
    extends RealAttribute
{
    public static final ShipBonusRole8 INSTANCE = new ShipBonusRole8();

    @Override
    public int getId() {
        return  1688;
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
        return "ShipBonusRole8";
    }
}
