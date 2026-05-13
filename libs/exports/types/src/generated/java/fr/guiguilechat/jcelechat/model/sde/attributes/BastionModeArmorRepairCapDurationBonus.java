package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Reduces the cycle time and capacitor cost of Armor Repairer modules for the bastion module. 
 */
public class BastionModeArmorRepairCapDurationBonus
    extends IntAttribute
{
    public static final BastionModeArmorRepairCapDurationBonus INSTANCE = new BastionModeArmorRepairCapDurationBonus();

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
        return "BastionModeArmorRepairCapDurationBonus";
    }
}
