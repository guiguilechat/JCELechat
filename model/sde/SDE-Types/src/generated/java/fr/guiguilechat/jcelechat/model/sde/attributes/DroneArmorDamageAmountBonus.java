package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to drone armor repair amount
 */
public class DroneArmorDamageAmountBonus
    extends IntAttribute
{
    public static final DroneArmorDamageAmountBonus INSTANCE = new DroneArmorDamageAmountBonus();

    @Override
    public int getId() {
        return  1219;
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
        return "DroneArmorDamageAmountBonus";
    }
}
