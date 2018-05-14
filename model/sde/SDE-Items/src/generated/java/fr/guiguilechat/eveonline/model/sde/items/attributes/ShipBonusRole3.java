package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Ship Role Bonus. Not multiplied by skills.
 */
public class ShipBonusRole3
    extends IntAttribute
{
    public final static ShipBonusRole3 INSTANCE = new ShipBonusRole3();

    @Override
    public int getId() {
        return  2300;
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
