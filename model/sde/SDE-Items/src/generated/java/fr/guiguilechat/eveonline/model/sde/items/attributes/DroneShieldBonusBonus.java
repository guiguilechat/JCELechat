package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Bonus to drone shield transport amount
 */
public class DroneShieldBonusBonus
    extends IntAttribute
{
    public final static DroneShieldBonusBonus INSTANCE = new DroneShieldBonusBonus();

    @Override
    public int getId() {
        return  1220;
    }

    @Override
    public int getCatId() {
        return  10;
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

    @Override
    public String toString() {
        return "DroneShieldBonusBonus";
    }
}
