package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Bonus to target painter effectiveness
 */
public class RookieTargetPainterStrengthBonus
    extends IntAttribute
{
    public static final RookieTargetPainterStrengthBonus INSTANCE = new RookieTargetPainterStrengthBonus();

    @Override
    public int getId() {
        return  1834;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "RookieTargetPainterStrengthBonus";
    }
}
