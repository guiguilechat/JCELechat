package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Bonus to fighter ROF (%)
 */
public class FighterBonusROFPercent
    extends RealAttribute
{
    public static final FighterBonusROFPercent INSTANCE = new FighterBonusROFPercent();

    @Override
    public int getId() {
        return  2337;
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
        return "FighterBonusROFPercent";
    }
}
