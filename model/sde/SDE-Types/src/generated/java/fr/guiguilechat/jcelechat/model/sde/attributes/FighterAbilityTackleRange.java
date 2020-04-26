package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Range
 */
public class FighterAbilityTackleRange
    extends IntAttribute
{
    public static final FighterAbilityTackleRange INSTANCE = new FighterAbilityTackleRange();

    @Override
    public int getId() {
        return  2239;
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
        return "FighterAbilityTackleRange";
    }
}
