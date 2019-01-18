package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


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
    public int getCatId() {
        return  0;
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
        return "RookieTargetPainterStrengthBonus";
    }
}
