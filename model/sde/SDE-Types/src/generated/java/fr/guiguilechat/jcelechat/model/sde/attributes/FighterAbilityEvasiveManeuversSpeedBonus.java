package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum Velocity Bonus
 */
public class FighterAbilityEvasiveManeuversSpeedBonus
    extends IntAttribute
{
    public static final FighterAbilityEvasiveManeuversSpeedBonus INSTANCE = new FighterAbilityEvasiveManeuversSpeedBonus();

    @Override
    public int getId() {
        return  2224;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityEvasiveManeuversSpeedBonus";
    }
}
