package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Ship Role Bonus. Not multiplied by skills.
 */
public class ShipBonusRole6
    extends RealAttribute
{
    public static final ShipBonusRole6 INSTANCE = new ShipBonusRole6();

    @Override
    public int getId() {
        return  2303;
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
        return "ShipBonusRole6";
    }
}
