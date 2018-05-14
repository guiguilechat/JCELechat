package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Multiplied by Caldari Carrier skill level.
 */
public class ShipBonusForceAuxiliaryC4
    extends IntAttribute
{
    public final static ShipBonusForceAuxiliaryC4 INSTANCE = new ShipBonusForceAuxiliaryC4();

    @Override
    public int getId() {
        return  2321;
    }

    @Override
    public int getCatId() {
        return  37;
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
}
