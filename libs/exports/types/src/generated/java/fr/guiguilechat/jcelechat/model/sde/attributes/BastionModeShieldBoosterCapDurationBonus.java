package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Reduces the cycle time and capacitor cost of Shield Booster modules for the bastion module. 
 */
public class BastionModeShieldBoosterCapDurationBonus
    extends IntAttribute
{
    public static final BastionModeShieldBoosterCapDurationBonus INSTANCE = new BastionModeShieldBoosterCapDurationBonus();

    @Override
    public int getId() {
        return  6187;
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
        return "BastionModeShieldBoosterCapDurationBonus";
    }
}
