package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Ship Role Bonus. Not multiplied by skills.
 */
public class ShipBonusRole1
    extends DoubleAttribute
{
    public final static ShipBonusRole1 INSTANCE = new ShipBonusRole1();

    @Override
    public int getId() {
        return  2298;
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
