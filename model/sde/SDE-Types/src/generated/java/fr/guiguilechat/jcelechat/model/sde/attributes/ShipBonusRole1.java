package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Ship Role Bonus. Not multiplied by skills.
 */
public class ShipBonusRole1
    extends DoubleAttribute
{
    public static final ShipBonusRole1 INSTANCE = new ShipBonusRole1();

    @Override
    public int getId() {
        return  2298;
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
        return "ShipBonusRole1";
    }
}
