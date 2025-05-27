package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Ship Role Bonus. Not multiplied by skills.
 */
public class ShipBonusRole3
    extends RealAttribute
{
    public static final ShipBonusRole3 INSTANCE = new ShipBonusRole3();

    @Override
    public int getId() {
        return  2300;
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
        return "ShipBonusRole3";
    }
}
