package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration
 */
public class FighterAbilityEvasiveManeuversDuration
    extends IntAttribute
{
    public static final FighterAbilityEvasiveManeuversDuration INSTANCE = new FighterAbilityEvasiveManeuversDuration();

    @Override
    public int getId() {
        return  2123;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityEvasiveManeuversDuration";
    }
}
