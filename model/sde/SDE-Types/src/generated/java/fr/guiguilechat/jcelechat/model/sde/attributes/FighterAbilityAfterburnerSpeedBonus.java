package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Maximum Velocity Bonus
 */
public class FighterAbilityAfterburnerSpeedBonus
    extends IntAttribute
{
    public static final FighterAbilityAfterburnerSpeedBonus INSTANCE = new FighterAbilityAfterburnerSpeedBonus();

    @Override
    public int getId() {
        return  2151;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  100.0;
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
        return "FighterAbilityAfterburnerSpeedBonus";
    }
}
