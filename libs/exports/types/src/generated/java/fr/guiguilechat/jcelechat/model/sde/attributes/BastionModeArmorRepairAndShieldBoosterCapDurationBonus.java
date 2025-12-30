package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Reduces the cycle time and capacitor cost of Shield Booster and Armor Repairer modules for the bastion module. 
 */
public class BastionModeArmorRepairAndShieldBoosterCapDurationBonus
    extends IntAttribute
{
    public static final BastionModeArmorRepairAndShieldBoosterCapDurationBonus INSTANCE = new BastionModeArmorRepairAndShieldBoosterCapDurationBonus();

    @Override
    public int getId() {
        return  5964;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "BastionModeArmorRepairAndShieldBoosterCapDurationBonus";
    }
}
