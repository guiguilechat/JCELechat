package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to drone shield transport amount
 */
public class DroneShieldBonusBonus
    extends IntAttribute
{
    public static final DroneShieldBonusBonus INSTANCE = new DroneShieldBonusBonus();

    @Override
    public int getId() {
        return  1220;
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
